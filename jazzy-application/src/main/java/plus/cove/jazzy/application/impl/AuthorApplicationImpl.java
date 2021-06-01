package plus.cove.jazzy.application.impl;

import com.github.pagehelper.PageRowBounds;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.component.PageHelper;
import plus.cove.infrastructure.component.PageModel;
import plus.cove.infrastructure.component.PageResult;
import plus.cove.infrastructure.jwt.JwtResult;
import plus.cove.infrastructure.jwt.JwtUtils;
import plus.cove.jazzy.application.AuthorApplication;
import plus.cove.jazzy.domain.entity.account.Account;
import plus.cove.jazzy.domain.entity.account.Activation;
import plus.cove.jazzy.domain.entity.user.Author;
import plus.cove.jazzy.domain.entity.user.UserEvent;
import plus.cove.jazzy.domain.entity.user.UserStatus;
import plus.cove.jazzy.domain.exception.AccountError;
import plus.cove.jazzy.domain.exception.TravellerError;
import plus.cove.jazzy.domain.principal.UserPrincipal;
import plus.cove.jazzy.domain.principal.UserRequest;
import plus.cove.jazzy.domain.view.*;
import plus.cove.jazzy.repository.AccountRepository;
import plus.cove.jazzy.repository.ActivationRepository;
import plus.cove.jazzy.repository.AuthorRepository;

import java.util.List;


/**
 * 旅行者应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class AuthorApplicationImpl implements AuthorApplication {
    @Autowired
    AuthorRepository authorRep;
    @Autowired
    AccountRepository accountRep;
    @Autowired
    ActivationRepository activationRep;

    @Autowired
    PageHelper pageHelper;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ApplicationContext appContext;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActionResult signup(UserSignupInput input) {
        ActionResult result = ActionResult.success();

        // 检查是否存在
        Account dbAccount = accountRep.selectByName(input.getUserName());
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
        Activation activation = Activation.create(author.getId().toString(), authCode, 7 * 24 * 60L);
        activationRep.insert(activation);

        // 生成token
        JwtResult jwt = jwtUtils.create(author.getId().toString());
        UserLoginOutput output = new UserLoginOutput(jwt.getToken(), jwt.getExpire(), author.getAvatar());

        // 发送邮件
        appContext.publishEvent(new UserEvent(account.getName()));
        // 记录日志
        UserRequest userRequest = input.getRequest();
        userRequest.withPrincipal(UserPrincipal.init(author.getId(), author.getName()));
        appContext.publishEvent(userRequest);

        result.succeed(output);
        return result;
    }

    @Override
    public ActionResult login(UserLoginInput input) {
        ActionResult result = ActionResult.success();

        // 账号是否存在
        Account dbAccount = accountRep.selectByName(input.getUserName());
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
        Author author = authorRep.selectById(dbAccount.getId());
        if (author == null) {
            result.fail(TravellerError.INVALID_USER);
            return result;
        }

        // 生成token
        JwtResult jwt = jwtUtils.create(author.getId().toString());
        UserLoginOutput output = new UserLoginOutput(jwt.getToken(), jwt.getExpire(), author.getAvatar());
        result.succeed(output);

        // 记录日志
        UserRequest userRequest = input.getRequest();
        userRequest.withPrincipal(UserPrincipal.init(author.getId(), author.getName()));
        appContext.publishEvent(userRequest);
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

    @Override
    public PageResult loadMany(AuthorListInput input, PageModel page) {
        List<AuthorListOutput> list = authorRep.selectMany(input, pageHelper.<PageRowBounds>fromModel(page));
        return pageHelper.toResult(list);
    }
}
