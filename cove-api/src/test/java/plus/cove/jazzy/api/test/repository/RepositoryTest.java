package plus.cove.jazzy.api.test.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import plus.cove.jazzy.api.ApiApplication;
import plus.cove.jazzy.domain.entity.user.Account;
import plus.cove.jazzy.domain.entity.user.UserStatus;
import plus.cove.jazzy.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class RepositoryTest {
    @Autowired
    private AccountRepository accountRep;

    private Account account;

    @Before
    public void create() {
        account = Account.create("User", "PASSWORD");
        account.setId(1L);
        System.out.println(account.getId());
    }

    @Test
    public void insert() {
        int count = accountRep.insert(account);
        Assert.isTrue(count > 0, "插入测试");
    }

    @Test
    public void update() {
        Account account = new Account();
        account.setId(1L);
        account.setStatus(UserStatus.DISABLED);
        int count = accountRep.updateById(account);
        Assert.isTrue(count > 0, "更新测试");
    }

    @Test
    public void delete() {
        int count = accountRep.deleteById(1L);
        Assert.isTrue(count > 0, "删除操作");
    }

    @Test
    public void custom() {
        Account account = accountRep.selectByName("User");
        Assert.notNull(account, "自定义查询");
    }

    @Test
    public void select() {
        Wrapper<Account> query = new LambdaQueryWrapper<Account>()
                .eq(Account::getName, "User");
        List<Account> accounts = accountRep.selectList(query);
        Assert.isTrue(accounts.size() == 1, "自定义查询条件");
    }
}
