package com.carbybus.cove.test.infrastructure;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.infrastructure.configuration.UniteHttpConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class UniteConfigTest {
    @Autowired
    private UniteHttpConfig config;


    @Test
    public void httpConfig() {
        Assert.notNull(config, "HTTP配置");
        String[] headers = config.getCorsAllowedHeaders();
        String[] origins = config.getCorsAllowedOrigins();
        // 默认*
        String[] methods = config.getCorsAllowedMethods();

        Assert.notNull(headers, "跨域");
        Assert.notNull(origins, "跨域");
    }
}
