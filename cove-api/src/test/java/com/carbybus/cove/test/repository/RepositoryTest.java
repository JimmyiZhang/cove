package com.carbybus.cove.test.repository;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.domain.entity.account.Account;
import com.carbybus.cove.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class RepositoryTest {
    @Autowired
    private AccountRepository accountRep;

    private Account account;

    @Before
    public void create() {
        account = Account.create("User", "PASSWORD")
                .setPassword("PASSWORD")
                .setSalt("SALT");

        System.out.println(account.getId());
    }

    @Test
    public void insert() {
        int count = accountRep.insert(account);
        Assert.isTrue(count > 0, "插入测试");
    }
}
