package com.carbybus.cove.test.sharding;

import com.carbybus.cove.api.ApiApplication;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.MasterSlaveDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class DataSourceTest {
    @Resource
    private DataSource dataSource;

    @Test
    public void link() {
        Assert.notNull(dataSource, "DataSource");
        System.out.println(dataSource.toString());

        boolean isMS = dataSource instanceof MasterSlaveDataSource;
        Assert.isTrue(isMS, "MasterSlaveDataSource");

        for (DataSource each : ((MasterSlaveDataSource) dataSource).getDataSourceMap().values()) {
            HikariDataSource ds = (HikariDataSource) each;

            System.out.println(ds.getPoolName());
        }
    }
}
