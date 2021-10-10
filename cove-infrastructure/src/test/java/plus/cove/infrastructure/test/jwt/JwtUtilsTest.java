package plus.cove.infrastructure.test.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import plus.cove.infrastructure.jwt.JwtResult;
import plus.cove.infrastructure.jwt.JwtUtils;
import plus.cove.infrastructure.jwt.UniteJwtConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        UniteJwtConfig.class,
        JwtUtils.class },
        initializers = ConfigDataApplicationContextInitializer.class)
public class JwtUtilsTest {
    @Autowired
    UniteJwtConfig config;

    @Autowired
    JwtUtils utils;

    @Test
    public void config() {
        System.out.println(config);
        Assertions.assertNotNull(config);
    }

    @Test
    public void create() {
        System.out.println(config.getTokenSecret());

        JwtResult result = utils.create("10000", "MANAGER", "");
        String token = result.getToken();
        System.out.println(token);

        Assertions.assertTrue(!token.isEmpty());
    }
}
