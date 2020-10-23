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

import com.gongzuolaile.infrastructure.utils.StringHelper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 数据源加载接口
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
@Configuration
public class CustomerDataSourceProvider {
    @Autowired
    private Environment environment;

    /**
     * 主数据库
     */
    private String primary = "primary";

    /**
     * 获取主数据库
     *
     * @return
     */
    public String getPrimary() {
        return this.primary;
    }

    private String getKey(String key, String name) {
        return "spring.datasource." + key + "." + StringHelper.caseToMidline(name);
    }

    private Properties getKeyProperty(String key) {
        Properties property = new Properties();
        Field[] fields = CustomerDataSourceProperty.class.getDeclaredFields();
        for (Field field : fields) {
            String proKey = field.getName();

            String envKey = getKey(key, proKey);
            if (environment.containsProperty(envKey)) {
                property.setProperty(proKey, environment.getProperty(envKey));
                continue;
            }

            // 主键代替
            envKey = getKey(this.primary, proKey);
            if (environment.containsProperty(envKey)) {
                property.setProperty(proKey, environment.getProperty(envKey));
            }
        }

        return property;
    }

    private DataSource getDataSource(String key) {
        Properties property = this.getKeyProperty(key);
        HikariConfig config = new HikariConfig(property);
        return new HikariDataSource(config);
    }

    /**
     * 获取数据源
     * 使用默认参数填充
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public DataSource buildDataSource(CustomerDataSourceProperty prop) {
        Properties property = this.getKeyProperty(this.primary);
        HikariConfig config = prop.toHikariConfig(property);
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    /**
     * 加载所有数据源
     *
     * @return 所有数据源，key为数据源名称
     */
    public Map<String, DataSource> loadDataSources() {
        // 判断是否为多数据源，为空则使用原数据源方式
        String multiple = environment.getProperty("spring.datasource.names", this.primary);
        String[] names = multiple.split(",");

        // 获取数据源
        Map<String, DataSource> dataSources = new HashMap<>(names.length);
        for (int i = 0; i < names.length; i++) {
            String key = names[i];
            // todo: 多个数据源且存在primary数据源，则primary仅作为参考数据源
            //if (names.length > 1 && key.equals(this.primary)) {
            //    continue;
            //}

            DataSource value = getDataSource(key);
            dataSources.put(key, value);
        }

        return dataSources;
    }
}
