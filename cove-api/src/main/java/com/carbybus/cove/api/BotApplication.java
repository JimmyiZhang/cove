package com.carbybus.cove.api;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 启动类
 * 提供DispatcherServlet的web.xml或者Servlet初始类
 *
 * @author jimmy.zhang
 * @date 2019/4/19
 */
public class BotApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(com.carbybus.cove.api.ApiApplication.class);
    }
}
