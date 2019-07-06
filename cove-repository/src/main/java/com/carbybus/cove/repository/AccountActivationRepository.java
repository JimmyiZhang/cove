package com.carbybus.cove.repository;


import com.carbybus.cove.domain.entity.user.Account;
import com.carbybus.cove.domain.entity.user.AccountActivation;
import com.carbybus.infrastructure.component.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 账号激活码仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface AccountActivationRepository extends BaseRepository<AccountActivation> {

    /**
     * 根据激活码获取用户激活信息
     *
     * @param code 激活码
     * @return 激活码对应的账号激活码实体，返回null则没有
     * @author jimmy.zhang
     * @date 2019/3/28
     */
    AccountActivation selectByCode(String code);
}
