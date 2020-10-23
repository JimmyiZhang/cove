package plus.cove.infrastructure.test.jwt;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plus.cove.infrastructure.jwt.JwtResult;
import plus.cove.infrastructure.jwt.JwtUtils;
import plus.cove.infrastructure.jwt.UniteJwtConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UniteJwtConfig.class, JwtUtils.class},
        initializers = ConfigFileApplicationContextInitializer.class)
public class JwtUtilsTest {
    @Autowired
    UniteJwtConfig config;

    @Autowired
    JwtUtils utils;

    @Test
    public void config() {
        System.out.println(config);
        Assert.assertNotNull(config);
    }

    @Test
    public void create() {
        System.out.println(config.getTokenSecret());

        JwtResult result = utils.create("10000", "MANAGER");
        String token = result.getToken();
        System.out.println(token);

        Assert.assertTrue(!token.isEmpty());
    }
}
