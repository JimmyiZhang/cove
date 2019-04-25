package com.carbybus.cove.test.sharding;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.domain.entity.Message;
import com.carbybus.cove.domain.entity.company.Account;
import com.carbybus.cove.repository.AccountRepository;
import com.carbybus.cove.repository.EmployeeRepository;
import com.carbybus.cove.repository.MessageRepository;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;


// 基于暗示分片测试
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class ShardingHintTest {
    @Autowired
    private AccountRepository accountRep;

    // 写分片
    @Test
    public void masterRouteTransaction() {
        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();

        Account account1 = Account.create()
                .setPassword("PASSWORD")
                .setSalt("SALT")
                .setUserName("USER_MS");

        Long id = account1.getId();
        System.out.println(id);
        // master
        accountRep.insert(account1);
        // slave
        Account account2 = accountRep.selectById(id);
        Assert.notNull(account2, "账号");
    }
}
