package com.gongzuolaile.infrastructure.datasource;

import com.gongzuolaile.infrastructure.exception.BusinessException;
import com.gongzuolaile.infrastructure.exception.ConfigError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义数据源
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Slf4j
@Component
public class CustomerRoutingDataSource extends AbstractRoutingDataSource implements InitializingBean, DisposableBean {
    /**
     * 所有数据库
     */
    private final Map<String, DataSource> dataSourceMap = new HashMap<>();

    @Autowired
    private CustomerDataSourceProvider provider;

    @Override
    public DataSource determineDataSource() {
        String key = CustomerDataSourceHolder.getKey();

        return getDataSource(key);
    }

    /**
     * 获取数据源
     *
     * @param key 数据源名称
     * @return 数据源
     */
    public DataSource getDataSource(String key) {
        if (StringUtils.isEmpty(key) || !dataSourceMap.containsKey(key)) {
            throw new BusinessException(ConfigError.EMPTY_DATASOURCE);
        }

        log.debug("product-datasource switch to the datasource {}", key);
        return dataSourceMap.get(key);
    }

    /**
     * 添加数据源
     *
     * @param key        数据源名称
     * @param dataSource 数据源
     */
    public synchronized void addDataSource(String key, DataSource dataSource) {
        if (!dataSourceMap.containsKey(key)) {
            dataSourceMap.put(key, dataSource);
        } else {
            log.warn("product-datasource - load a datasource named [{}] failed, because it already exist", key);
        }
    }

    /**
     * 删除数据源
     *
     * @param key 数据源名称
     */
    public synchronized void removeDataSource(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new BusinessException(ConfigError.EMPTY_DATASOURCE);
        }
        if (provider.getPrimary().equals(key)) {
            throw new BusinessException(ConfigError.PRIMARY_DATASOURCE_REMOVE);
        }

        if (dataSourceMap.containsKey(key)) {
            DataSource dataSource = dataSourceMap.get(key);
            try {
                closeDataSource(dataSource);
            } catch (Exception e) {
                log.error("product-datasource - remove the datasource {} failed", key);
            }
            dataSourceMap.remove(key);
            log.info("product-datasource - remove the datasource named {} success", key);
        } else {
            log.warn("product-datasource - could not find a datasource named {}", key);
        }
    }

    /**
     * 关闭数据源
     */
    private void closeDataSource(DataSource dataSource) throws Exception {
        Class<? extends DataSource> clazz = dataSource.getClass();
        Method closeMethod = clazz.getDeclaredMethod("close");
        closeMethod.invoke(dataSource);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, DataSource> dataSources = provider.loadDataSources();

        // 自定义数据源
        for (Map.Entry<String, DataSource> dsItem : dataSources.entrySet()) {
            dataSourceMap.put(dsItem.getKey(), dsItem.getValue());
        }

        // 放入上下文
        CustomerDataSourceHolder.setKey(provider.getPrimary());
    }

    @Override
    public void destroy() throws Exception {
        for (Map.Entry<String, DataSource> item : dataSourceMap.entrySet()) {
            closeDataSource(item.getValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(dataSourceMap.keySet().size() * 3);
        for (String key : dataSourceMap.keySet()) {
            sb.append("datasource[key=");
            sb.append(key);
            sb.append("],");
        }

        return sb.toString();
    }
}
