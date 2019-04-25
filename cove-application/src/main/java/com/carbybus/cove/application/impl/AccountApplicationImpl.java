package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.AccountApplication;
import com.carbybus.cove.domain.entity.company.Account;
import com.carbybus.cove.domain.exception.AccountError;
import com.carbybus.cove.repository.AccountRepository;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.impl.DefaultApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 账号应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class AccountApplicationImpl extends DefaultApplication<AccountRepository, Account> implements AccountApplication {
    /**
     * 创建账号
     *
     * @param account 账号实体
     * @return 创建账号的结果
     * @author jimmy.zhang
     * @date 2019-03-28
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ActionResult create(Account account) {
        ActionResult result = new ActionResult();

        // 检查是否存在
        Account dbAccount = getByName(account.getUserName());
        if (dbAccount != null) {
            result.fail(AccountError.EXISTED_ACCOUNT);
            return result;
        }

        // 保存数据
        this.save(account);
        result.succeed();
        return result;
    }

    /**
     * 根据名称获取账号
     *
     * @param name 账号名称
     * @return 对应名称的账号实体，没有返回 null
     * @author Liuyoushi
     * @date 2019-04-08
     */
    @Override
    public Account getByName(String name) {
        return repository.selectByName(name);
    }
}
