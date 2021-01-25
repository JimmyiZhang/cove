package com.gongzuolaile.agent.api.config;

import com.gongzuolaile.infrastructure.datasource.CustomerDataSourceHolder;
import com.gongzuolaile.infrastructure.datasource.CustomerDataSourceProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 分库过滤器
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @date 2019-09-06
 */
@Slf4j
@Component
public class WebShardingFilter extends OncePerRequestFilter {
    @Autowired
    CustomerDataSourceProcessor dataSourceProcessor;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String productCode = dataSourceProcessor.getProductCode(request);

        // todo: 测试阶段，没有默认是primary
        if (StringUtils.isEmpty(productCode)) {
            productCode = "primary";
        }

        CustomerDataSourceHolder.setKey(productCode);
        chain.doFilter(request, response);
    }
}
