package plus.cove.jazzy.api.test.infrastructure;

import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.reporting.providers.ConsoleReportGenerator;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.util.Assert;
import plus.cove.infrastructure.generator.KeyGeneratorBuilder;

/**
 * 主键生成器测试
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
public class KeyGeneratorBuilderTest {
    @Rule
    public JUnitPerfRule perfTestRule = new JUnitPerfRule(new ConsoleReportGenerator());

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

    @Test
    @JUnitPerfTest(threads = 5, durationMs = 1000, warmUpMs = 0, rampUpPeriodMs = 0, maxExecutionsPerSecond = 100)
    public void buildSome() throws InterruptedException {
        long id = KeyGeneratorBuilder.INSTANCE.build();
        System.out.println(id);
        Assert.isTrue(id > 0, "id测试");
    }
}
