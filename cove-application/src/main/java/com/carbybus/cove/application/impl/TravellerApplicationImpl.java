package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.TravellerApplication;
import com.carbybus.cove.domain.entity.account.Account;
import com.carbybus.cove.domain.entity.account.Traveller;
import com.carbybus.cove.domain.exception.AccountError;
import com.carbybus.cove.domain.exception.TravellerError;
import com.carbybus.cove.domain.view.TravellerLoginInput;
import com.carbybus.cove.domain.view.TravellerLoginOutput;
import com.carbybus.cove.domain.view.TravellerSignupInput;
import com.carbybus.cove.repository.AccountRepository;
import com.carbybus.cove.repository.TravellerRepository;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.impl.DefaultApplication;
import com.carbybus.infrastructure.jwt.JwtResult;
import com.carbybus.infrastructure.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 旅行者应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class TravellerApplicationImpl extends DefaultApplication<TravellerRepository, Traveller> implements TravellerApplication {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AccountRepository accountRep;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ActionResult signup(TravellerSignupInput input) {
        ActionResult result = ActionResult.OK;

        // 检查是否存在
        Account dbAccount = accountRep.selectByName(input.getEmail());
        if (dbAccount != null) {
            result.fail(AccountError.EXISTED_ACCOUNT);
            return result;
        }

        Traveller traveller = input.convertToTraveller();
        Account account = input.convertToAccount();
        account.setId(traveller.getId());

        // 保存数据
        repository.insert(traveller);
        accountRep.insert(account);
        result.succeed();
        return result;
    }

    @Override
    public ActionResult login(TravellerLoginInput input) {
        ActionResult result = ActionResult.OK;

        // 账号是否存在
        Account dbAccount = accountRep.selectByName(input.getEmail());
        if (dbAccount == null) {
            result.fail(AccountError.INVALID_NAME);
            return result;
        }

        // 密码是否正确
        boolean isLogin = dbAccount.valid(input.getPassword());
        if (!isLogin) {
            result.fail(AccountError.INVALID_PASSWORD);
            return result;
        }

        // 用户是否存在
        Traveller traveller = repository.selectById(dbAccount.getId());
        if (traveller == null) {
            result.fail(TravellerError.INVALID_USER);
            return result;
        }

        // 生成token
        JwtResult jwt = jwtUtils.create(traveller.getId().toString());

        TravellerLoginOutput output = new TravellerLoginOutput(jwt.getToken(), jwt.getExpire());
        result.succeed(output);

        return result;
    }
}
