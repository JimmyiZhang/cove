package plus.cove.jazzy.api.test.entity;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import plus.cove.infrastructure.jwt.JwtClaim;
import plus.cove.infrastructure.jwt.JwtResult;
import plus.cove.infrastructure.jwt.JwtUtils;
import plus.cove.infrastructure.jwt.UniteJwtConfig;
import plus.cove.jazzy.api.ApiApplication;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class AccountTest {
    @Autowired
    UniteJwtConfig jwtConfig;
    @Autowired
    JwtUtils jwtUtils;

    @Test
    public void create() {
        jwtConfig.setTokenSecret("7p9izREdj3cAAQR7HyDCoMDv9dDDtzQLbJ1ScicADAclc");
        jwtConfig.setTokenExpired(Duration.ofDays(30));

        JwtResult result = jwtUtils.create("10000", "ACTUATOR", "devops");
        System.out.println(result);

        Assertions.assertNotNull(result);
    }

    @Test
    public void decode(){
        jwtConfig.setTokenSecret("7p9izREdj3cAAQR7HyDCoMDv9dDDtzQLbJ1ScicADAclc");
        jwtConfig.setTokenExpired(Duration.ofDays(30));

        JwtClaim claim = jwtUtils.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYXp6eSIsImlzcyI6ImNvdmUiLCJqdGkiOiJ3aHA5bmxzYndoIiwiaWF0IjoiMTYzNDI4ODc4MyIsImV4cCI6IjE2MzY4ODA3ODMiLCJpZCI6IjEwMDAwIiwiYWN0IjoiQUNUVUFUT1IiLCJleHQiOiJkZXZvcHMifQ.58znGxD40cNwPs9WvxVnRyLB-bbq28eqMAv26N4J63Ky4yCCvsObJFvSWYIUZsCXZxJFxtBLOORvefqCprT9SQ");
        System.out.println(claim);

        Assertions.assertNotNull(claim);
    }
}
