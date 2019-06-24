package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.MailApplication;
import com.carbybus.cove.application.TravellerApplication;
import com.carbybus.cove.domain.entity.user.Account;
import com.carbybus.cove.domain.entity.user.AccountActivation;
import com.carbybus.cove.domain.entity.user.Traveller;
import com.carbybus.cove.domain.exception.AccountError;
import com.carbybus.cove.domain.exception.TravellerError;
import com.carbybus.cove.domain.view.UserLoginInput;
import com.carbybus.cove.domain.view.UserLoginOutput;
import com.carbybus.cove.domain.view.UserSignupInput;
import com.carbybus.cove.repository.AccountActivationRepository;
import com.carbybus.cove.repository.AccountRepository;
import com.carbybus.cove.repository.TravellerRepository;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.impl.DefaultApplication;
import com.carbybus.infrastructure.email.EmailUtils;
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
    private EmailUtils emailUtils;
    @Autowired
    private MailApplication mailApp;

    @Autowired
    private AccountRepository accountRep;
    @Autowired
    private AccountActivationRepository AccountActivationRep;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ActionResult signup(UserSignupInput input) {
        ActionResult result = ActionResult.OK;

        // 检查是否存在
        Account dbAccount = accountRep.selectByName(input.getAccount());
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

        // 保存激活码
        AccountActivation activation = AccountActivation.create(traveller.getId());
        AccountActivationRep.insert(activation);

        // 发送邮件
        mailApp.sendActivateMail(input.getAccount(), activation.getUserCode());

        result.succeed();
        return result;
    }

    @Override
    public ActionResult login(UserLoginInput input) {
        ActionResult result = ActionResult.OK;

        // 账号是否存在
        Account dbAccount = accountRep.selectByName(input.getName());
        if (dbAccount == null) {
            result.fail(AccountError.INVALID_NAME);
            return result;
        }

        // 密码是否正确
        boolean isLogin = dbAccount.validate(input.getPassword());
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

        UserLoginOutput output = new UserLoginOutput(jwt.getToken(), jwt.getExpire());
        result.succeed(output);

        return result;
    }
}
