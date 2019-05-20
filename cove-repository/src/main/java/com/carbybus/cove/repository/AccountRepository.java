package com.carbybus.cove.repository;


import com.carbybus.cove.domain.entity.account.Account;
import com.carbybus.infrastructure.component.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 账号仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface AccountRepository extends BaseRepository<Account> {

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
