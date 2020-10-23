package com.gongzuolaile.infrastructure.datasource;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理器
 * 用于获取数据源的key
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Component
@Slf4j
public final class CustomerDataSourceProcessor {
    private static final String HEADER_KEY = "Product-Code";
    private static final String QUERY_KEY = "product-code";

    /**
     * 获取当前DataSource
     *
     * @return data source key
     */
    public String getProductCode(HttpServletRequest request) {
        String code = request.getHeader(HEADER_KEY);
        if (StringUtils.isEmpty(code)) {
            code = request.getParameter(QUERY_KEY);
        }

        log.debug("product code is : {}", code);
        return code;
    }
}
