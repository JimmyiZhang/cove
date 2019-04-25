package com.carbybus.cove.test.sharding;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.application.AccountApplication;
import com.carbybus.cove.domain.entity.company.Account;
import com.carbybus.cove.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

// 读写分离测试
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class ShardingSplittingTest {
    @Autowired
    private AccountRepository accountRep;

    @Autowired
    private AccountApplication accountApp;

    @Test
    public void onlyWrite() {
        Account account = Account.create()
                .setPassword("PASSWORD")
                .setSalt("SALT")
                .setUserName("USER_Write");

        System.out.println(account.getId());

        accountRep.insert(account);
        Assert.notNull(account, "账号");
    }

    @Test
    public void onlyRead() {
        Account account = accountRep.selectById(1);

        Assert.notNull(account, "账号");
        System.out.println(account.getUserName());
    }

    @Test
    public void notTransaction() {
        Account account1 = Account.create()
                .setPassword("PASSWORD")
                .setSalt("SALT")
                .setUserName("USER_NT");

        Long id = account1.getId();
        System.out.println(id);
        // master
        accountRep.insert(account1);
        // slave
        Account account2 = accountRep.selectById(id);
        Assert.isNull(account2, "账号");
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void inTransaction() {
        Account account1 = Account.create()
                .setPassword("PASSWORD")
                .setSalt("SALT")
                .setUserName("USER_IT");
        System.out.println(account1.getId());
        // master
        accountApp.create(account1);
        // master
        Account account2 = accountRep.selectById(account1.getId());
        Assert.notNull(account2, "账号");
    }
}
