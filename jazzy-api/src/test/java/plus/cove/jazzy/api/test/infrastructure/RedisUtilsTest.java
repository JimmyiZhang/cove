package plus.cove.jazzy.api.test.infrastructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import plus.cove.infrastructure.redis.RedisUtils;
import plus.cove.infrastructure.utils.LongHelper;
import plus.cove.jazzy.api.ApiApplication;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class RedisUtilsTest {
    private String STR_KEY = "COUNT";
    private String SET_KEY = "TOP3";

    @Autowired
    RedisUtils redisUtils;

    @Before
    public void init() {
        // 初始化3个
        redisUtils.zAdd(SET_KEY, "beijing", 1);
        redisUtils.zAdd(SET_KEY, "tianjin", 2);
        redisUtils.zAdd(SET_KEY, "shanghai", 3);
    }

    public void add() {
        // 获取是否存在
        Long has = redisUtils.zRank(SET_KEY, "chongqing");

        Double score = 4D;
        // 判断是否存在
        if (LongHelper.isEmpty(has)) {
            // 不存在，获取最小的进行比较
            Set<String> lastSet = redisUtils.zRange(SET_KEY, 0, 0);
            String lastOne = lastSet.stream().findFirst().get();
            Double lastScore = redisUtils.zScore(SET_KEY, lastOne);
            if (lastScore.intValue() < 4) {
                redisUtils.zAdd(SET_KEY, "chongqing", score);
                redisUtils.zRemoveRange(SET_KEY, 0, 0);
            }
        } else {
            // 存在，则更新原数据
            score = redisUtils.zIncrementScore(SET_KEY, "chongqing", 1);
        }

        Assert.assertTrue(score.intValue() > 4);
    }

    @Test
    public void action(){
        redisUtils.execute(rd->{
            rd.opsForValue().set(STR_KEY, "1");
            rd.opsForValue().increment(STR_KEY);
        });
    }
}
