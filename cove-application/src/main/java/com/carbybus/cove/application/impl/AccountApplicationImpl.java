package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.AccountApplication;
import com.carbybus.cove.domain.entity.company.Account;
import com.carbybus.cove.domain.exception.AccountError;
import com.carbybus.cove.domain.view.UserLoginInput;
import com.carbybus.cove.domain.view.UserLoginOutput;
import com.carbybus.cove.domain.view.UserRefreshInput;
import com.carbybus.cove.repository.AccountRepository;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.impl.DefaultApplication;
import com.carbybus.infrastructure.jwt.JwtResult;
import com.carbybus.infrastructure.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private JwtUtils jwtUtils;

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

    @Override
    public Account getByName(String name) {
        return repository.selectByName(name);
    }

    @Override
    public ActionResult accessToken(UserLoginInput input) {
        ActionResult result = ActionResult.OK;

        if (input.getUserName().equals("user") && input.getPassword().equals("123456")) {
            JwtResult token = jwtUtils.create("user");

            UserLoginOutput output = new UserLoginOutput();
            output.setToken(token.getToken());
            output.setExpire(token.getExpire());

            result.succeed(output);
        } else {
            result.fail(AccountError.INVALID_NAME);
        }

        return result;
    }

    @Override
    public ActionResult refreshToken(UserRefreshInput input) {
        ActionResult result = ActionResult.OK;

        JwtResult token = jwtUtils.refresh(input.getToken());

        UserLoginOutput output = new UserLoginOutput();
        output.setToken(token.getToken());
        output.setExpire(token.getExpire());

        result.succeed(output);
        return result;
    }
}
