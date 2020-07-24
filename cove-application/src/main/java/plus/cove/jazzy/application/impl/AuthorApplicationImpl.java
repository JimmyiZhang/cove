package plus.cove.jazzy.application.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.email.EmailUtils;
import plus.cove.infrastructure.jwt.JwtResult;
import plus.cove.infrastructure.jwt.JwtUtils;
import plus.cove.jazzy.application.AuthorApplication;
import plus.cove.jazzy.application.MailApplication;
import plus.cove.jazzy.domain.entity.account.Account;
import plus.cove.jazzy.domain.entity.account.Activation;
import plus.cove.jazzy.domain.entity.user.Author;
import plus.cove.jazzy.domain.entity.user.UserStatus;
import plus.cove.jazzy.domain.exception.AccountError;
import plus.cove.jazzy.domain.exception.TravellerError;
import plus.cove.jazzy.domain.view.UserActiveInput;
import plus.cove.jazzy.domain.view.UserLoginInput;
import plus.cove.jazzy.domain.view.UserLoginOutput;
import plus.cove.jazzy.domain.view.UserSignupInput;
import plus.cove.jazzy.repository.AccountRepository;
import plus.cove.jazzy.repository.ActivationRepository;
import plus.cove.jazzy.repository.AuthorRepository;


/**
 * 旅行者应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class AuthorApplicationImpl implements AuthorApplication {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private MailApplication mailApp;
    @Autowired
    private AuthorRepository authorRep;
    @Autowired
    private AccountRepository accountRep;
    @Autowired
    private ActivationRepository activationRep;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActionResult signup(UserSignupInput input) {
        ActionResult result = ActionResult.success();

        // 检查是否存在
        Account dbAccount = accountRep.selectByName(input.getAccount());
        if (dbAccount != null) {
            result.fail(AccountError.EXISTED_ACCOUNT);
            return result;
        }

        Author author = input.convertToAuthor();
        Account account = input.convertToAccount();
        account.setId(author.getId());

        // 保存数据
        authorRep.insert(author);
        accountRep.insert(account);

        // 保存激活码
        String authCode = RandomStringUtils.randomAlphanumeric(12);
        Activation activation = Activation.create(author.getId().toString(), authCode);
        activationRep.insert(activation);

        // 发送邮件
        mailApp.sendActivateMail(input.getAccount(), activation.getAuthCode());

        result.succeed();
        return result;
    }

    @Override
    public ActionResult login(UserLoginInput input) {
        ActionResult result = ActionResult.success();

        // 账号是否存在
        Account dbAccount = accountRep.selectByName(input.getName());
        if (dbAccount == null) {
            result.fail(AccountError.INVALID_NAME);
            return result;
        }

        // 状态无效或已过期
        if (!dbAccount.checkStatus()) {
            result.fail(AccountError.INVALID_STATUS);
            return result;
        }

        // 密码是否正确
        boolean isLogin = dbAccount.checkPassword(input.getPassword());
        if (!isLogin) {
            result.fail(AccountError.INVALID_PASSWORD);
            return result;
        }

        // 用户是否存在
        Author author = authorRep.selectByPrimaryKey(dbAccount.getId());
        if (author == null) {
            result.fail(TravellerError.INVALID_USER);
            return result;
        }

        // 生成token
        JwtResult jwt = jwtUtils.create(author.getId().toString());
        UserLoginOutput output = new UserLoginOutput(jwt.getToken(), jwt.getExpire(), author.getAvatar());
        result.succeed(output);

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActionResult active(UserActiveInput input) {
        ActionResult result = ActionResult.success();

        // 查询激活信息
        Activation activation = activationRep.selectByAuth(input.getAuthCode());
        // 无效
        if (activation == null) {
            result.fail(AccountError.INVALID_ACTIVATION);
            return result;
        }

        // 过期
        if (!activation.isValid()) {
            result.fail(AccountError.EXPIRED_ACTIVATION);
            return result;
        }

        // 查询是否激活
        Long userId = Long.parseLong(activation.getUserCode());
        Account account = accountRep.selectById(userId);
        if (account != null && account.getStatus() == UserStatus.ACTIVE) {
            result.fail(AccountError.USED_ACTIVATION);
            return result;
        }

        // 激活用户
        account = new Account();
        account.setId(userId);
        account.active();
        accountRep.updateById(account);
        return result;
    }
}
