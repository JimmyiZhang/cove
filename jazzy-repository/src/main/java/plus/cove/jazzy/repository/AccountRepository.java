package plus.cove.jazzy.repository;


import org.springframework.stereotype.Repository;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.account.Account;

/**
 * 账号仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface AccountRepository extends MybatisRepository<Account> {
    /**
     * 根据用户名获取账号
     *
     * @param name 用户名
     * @return 用户名对应的账号，没有记录返回 null
     * @author jimmy.zhang
     * @date 2019/3/28
     */
    Account selectByName(String name);
}
