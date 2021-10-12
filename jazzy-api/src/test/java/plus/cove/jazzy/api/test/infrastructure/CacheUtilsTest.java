package plus.cove.jazzy.api.test.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import plus.cove.infrastructure.caching.CacheUtils;
import plus.cove.jazzy.api.ApiApplication;
import plus.cove.jazzy.domain.entity.global.District;
import plus.cove.jazzy.domain.entity.global.DistrictType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class CacheUtilsTest {

    @Autowired
    CacheUtils cacheUtils;

    @Test
    public void getString() {
        cacheUtils.put("VALUE#1000", "STRING", "ok");
        Object value = cacheUtils.get("VALUE#1000", "STRING");
        System.out.println(value);
        Assert.isTrue(value.toString().equals("ok"), "cache获取字符串");
    }

    @Test
    public void getObject() {
        District district = new District();
        district.setName("北京");
        district.setTitle("北京市");
        district.setType(DistrictType.CITY);

        cacheUtils.put("OBJ", "CITY", district);
        District value = cacheUtils.get("OBJ", "CITY", District.class);
        Assert.notNull(value, "cache获取对象");
        Assert.isTrue(value.getName().equals("北京"), "获取对象");
    }

    @Test
    public void getCity() {
        District district = new District();
        district.setName("北京");
        district.setTitle("北京市");
        district.setType(DistrictType.CITY);

        cacheUtils.put("CITY", "BJ", district);

        District value = cacheUtils.get("CITY", "BJ", District.class);
        System.out.println(value);

        Assert.notNull(value, "cache获取对象");
        Assert.isTrue(value.getName().equals("北京"), "获取对象");
    }
}
