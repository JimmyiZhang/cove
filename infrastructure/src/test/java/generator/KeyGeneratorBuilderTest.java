package generator;

import com.carbybus.infrastructure.generator.KeyGeneratorBuilder;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 主键生成器测试
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
public class KeyGeneratorBuilderTest {
    @Test
    public void build() {
        long id = KeyGeneratorBuilder.INSTANCE.build();
        System.out.println(id);

        Assert.isTrue(id > 0, "id测试");
    }

    @Test
    public void buildMany() throws InterruptedException {
        long id = KeyGeneratorBuilder.INSTANCE.build();

        // 生成多个应该均匀分布，不会偶数多，奇数少
        for (int i = 0; i < 100; i++) {
            id = KeyGeneratorBuilder.INSTANCE.build();
            System.out.println(id);
        }

        Assert.isTrue(id > 0, "id测试");
    }
}
