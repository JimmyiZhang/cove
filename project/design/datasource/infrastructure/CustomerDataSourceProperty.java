/**
 * Copyright © 2018 organization baomidou
 * <pre>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <pre/>
 */
package com.gongzuolaile.infrastructure.datasource;

import com.zaxxer.hikari.HikariConfig;
import lombok.Data;

import java.util.Properties;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 数据源加载接口
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
@Data
public class CustomerDataSourceProperty {
    private static final long CONNECTION_TIMEOUT = SECONDS.toMillis(30);
    private static final long VALIDATION_TIMEOUT = SECONDS.toMillis(5);
    private static final long IDLE_TIMEOUT = MINUTES.toMillis(10);
    private static final long MAX_LIFETIME = MINUTES.toMillis(30);
    private static final int DEFAULT_POOL_SIZE = 10;

    private String username;
    private String password;
    private String driverClassName;
    private String jdbcUrl;
    private String poolName;

    private String schema;
    private String catalog;
    private Long connectionTimeout;
    private Long validationTimeout;
    private Long idleTimeout;
    private Long leakDetectionThreshold;
    private Long maxLifetime;
    private Integer maximumPoolSize;
    private Integer minIdle;

    private Boolean isAutoCommit;
    private Boolean isReadOnly;
    private Boolean isIsolateInternalQueries;
    private Boolean isRegisterMbeans;
    private Boolean isAllowPoolSuspension;

    /**
     * 转换为HikariCP配置
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public HikariConfig toHikariConfig(Properties property) {
        if (property == null) {
            property = new Properties();
        }
        HikariConfig config = new HikariConfig(property);
        if (username != null) {
            config.setUsername(username);
        }
        if (password != null) {
            config.setPassword(password);
        }
        if (driverClassName != null) {
            config.setDriverClassName(driverClassName);
        }
        if (jdbcUrl != null) {
            config.setJdbcUrl(jdbcUrl);
        }
        if (poolName != null) {
            config.setPoolName(poolName);
        }
        if (username != null) {
            config.setUsername(username);
        }

        if (schema != null) {
            config.setSchema(schema);
        }
        if (catalog != null) {
            config.setCatalog(catalog);
        }

        if (connectionTimeout != null && !connectionTimeout.equals(CONNECTION_TIMEOUT)) {
            config.setConnectionTimeout(connectionTimeout);
        }
        if (validationTimeout != null && !validationTimeout.equals(VALIDATION_TIMEOUT)) {
            config.setValidationTimeout(validationTimeout);
        }
        if (idleTimeout != null && !idleTimeout.equals(IDLE_TIMEOUT)) {
            config.setIdleTimeout(idleTimeout);
        }

        if (leakDetectionThreshold != null) {
            config.setLeakDetectionThreshold(leakDetectionThreshold);
        }

        if (maxLifetime != null && !maxLifetime.equals(MAX_LIFETIME)) {
            config.setMaxLifetime(maxLifetime);
        }
        if (maximumPoolSize != null && !maximumPoolSize.equals(-1)) {
            config.setMaximumPoolSize(maximumPoolSize);
        }
        if (minIdle != null && !minIdle.equals(-1)) {
            config.setMinimumIdle(minIdle);
        }

        if (isAutoCommit != null && isAutoCommit.equals(Boolean.FALSE)) {
            config.setAutoCommit(false);
        }
        if (isReadOnly != null) {
            config.setReadOnly(isReadOnly);
        }
        if (isIsolateInternalQueries != null) {
            config.setIsolateInternalQueries(isIsolateInternalQueries);
        }
        if (isRegisterMbeans != null) {
            config.setRegisterMbeans(isRegisterMbeans);
        }
        if (isAllowPoolSuspension != null) {
            config.setAllowPoolSuspension(isAllowPoolSuspension);
        }

        return config;
    }
}
