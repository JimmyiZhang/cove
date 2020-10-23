package plus.cove.infrastructure.test.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import plus.cove.infrastructure.system.SystemRunTime;
import plus.cove.infrastructure.system.SystemUtils;
import plus.cove.infrastructure.system.UniteSystemConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UniteSystemConfig.class, SystemUtils.class},
        initializers = ConfigFileApplicationContextInitializer.class)
public class SystemUtilsTest {
    @Autowired
    UniteSystemConfig systemConfig;
    @Autowired
    SystemUtils systemUtils;

    @Test
    public void runTime(){
        SystemRunTime runtime = systemConfig.getRunTime();
        System.out.println(runtime);

        Assert.notNull(runtime,"运行环境");
    }

    @Test
    public void generateAuthCode(){
        String code = systemUtils.generateWordCode(12);
        System.out.println(code);
        Assert.isTrue(code.equals(systemConfig.getAuthCode()),"默认认证码");
    }
}
