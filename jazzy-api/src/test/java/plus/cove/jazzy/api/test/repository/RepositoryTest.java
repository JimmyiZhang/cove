package plus.cove.jazzy.api.test.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import plus.cove.jazzy.api.ApiApplication;
import plus.cove.jazzy.domain.entity.account.Account;
import plus.cove.jazzy.domain.entity.account.UserStatus;
import plus.cove.jazzy.repository.AccountRepository;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class RepositoryTest {
    @Autowired
    private AccountRepository accountRep;

    private Account account;

    @Before
    public void create() {
        account = Account.create("USER", "PASSWORD");
        account.setStatus(UserStatus.ACTIVE);
        System.out.println(account.getId());
    }

    @Test
    public void select(){
        Optional<Account> account = accountRep.selectById(71473214508761088L);
        Assert.assertNotNull(account);
    }

    @Test
    public void insert() {
        int count = accountRep.insert(account);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void update() {
        Account account = new Account();
        account.setId(1L);
        account.setStatus(UserStatus.DISABLED);
        int count = accountRep.updateAllById(account);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void delete() {
        int count = accountRep.deleteById(1L);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void custom() {
        Account account = accountRep.selectByName("User");
        Assert.assertNotNull(account);
    }
}
