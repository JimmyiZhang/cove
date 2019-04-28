package com.carbybus.cove.test.infrastructure;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.infrastructure.configuration.UniteConfig;
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
    private UniteConfig config;

    @Test
    public void httpConfig() {
        Assert.notNull(config, "配置");
        Assert.notNull(config.getHttpConfig(), "HTTP配置");

        UniteHttpConfig httpConfig = config.getHttpConfig();
        System.out.println(httpConfig.getCacheMaxAge());
    }

    @Test
    public void httpConfigCorsAllowedOrigins() {
        Assert.notNull(config, "配置");
        Assert.notNull(config.getHttpConfig(), "HTTP配置");
        String[] headers = config.getHttpConfig().getCorsAllowedHeaders();
        String[] origins = config.getHttpConfig().getCorsAllowedOrigins();
        // 默认*
        String[] methods = config.getHttpConfig().getCorsAllowedMethods();

        Assert.notNull(headers, "跨域");
        Assert.notNull(origins, "跨域");
    }
}
