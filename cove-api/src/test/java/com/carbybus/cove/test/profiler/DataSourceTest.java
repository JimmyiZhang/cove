package com.carbybus.cove.test.profiler;

import com.carbybus.cove.api.ApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class DataSourceTest {
    @Autowired
    private DataSource ds;

    @Test
    public void has(){
        Assert.notNull(ds,"数据源");

        System.out.println(ds.toString());
    }
}
