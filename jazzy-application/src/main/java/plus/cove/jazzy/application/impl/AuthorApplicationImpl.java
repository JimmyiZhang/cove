package plus.cove.jazzy.application.impl;

import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.component.PageModel;
import plus.cove.infrastructure.component.PageResult;
import plus.cove.infrastructure.jwt.JwtResult;
import plus.cove.infrastructure.jwt.JwtUtils;
import plus.cove.jazzy.application.AuthorApplication;
import plus.cove.jazzy.application.FacilityApplication;
import plus.cove.jazzy.domain.entity.account.Account;
import plus.cove.jazzy.domain.entity.account.UserEvent;
import plus.cove.jazzy.domain.entity.account.UserStatus;
import plus.cove.jazzy.domain.entity.author.Author;
import plus.cove.jazzy.domain.entity.global.Activation;
import plus.cove.jazzy.domain.exception.AccountError;
import plus.cove.jazzy.domain.exception.TravellerError;
import plus.cove.jazzy.domain.facility.LimitingCondition;
import plus.cove.jazzy.domain.facility.LimitingTarget;
import plus.cove.jazzy.domain.facility.VersioningCondition;
import plus.cove.jazzy.domain.facility.VersioningTarget;
import plus.cove.jazzy.domain.principal.UserPrincipal;
import plus.cove.jazzy.domain.principal.UserRequest;
import plus.cove.jazzy.domain.view.*;
import plus.cove.jazzy.repository.AccountRepository;
import plus.cove.jazzy.repository.AuthorRepository;
import plus.cove.jazzy.repository.GlobalActivationRepository;

import java.util.List;
import java.util.Optional;


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
    GlobalActivationRepository activationRep;

    @Autowired
    FacilityApplication facilityApp;

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

        Author author = input.toAuthor();
        Account account = input.toAccount(author.getId());

        // 保存数据
        authorRep.insert(author);
        accountRep.insert(account);

        // 保存激活码
        String authCode = RandomUtil.randomString(12);
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

        // 是否超过限流
        LimitingCondition limCondition = LimitingCondition.createWithDay(
                input.getUserName(), "login-failure", 3);
        LimitingTarget limiting = facilityApp.loadLimitingTarget(limCondition);
        boolean isLimit = LimitingTarget.exceedLimit(limiting);
        if (isLimit) {
            result.fail(AccountError.INVALID_LIMIT);
            return result;
        }

        // 是否重复请求
        VersioningCondition verCondition = VersioningCondition.from(input.getMessageCode());
        VersioningTarget versioning = facilityApp.loadVersioningTarget(verCondition);
        boolean isVersion = VersioningTarget.valid(versioning, input.getMessageRandom());
        if (isVersion) {
            result.fail(AccountError.INVALID_VERSION);
            return result;
        }

        // 账号是否存在
        Account dbAccount = accountRep.selectByName(input.getUserName());
        if (dbAccount == null) {
            result.fail(AccountError.INVALID_NAME);
            facilityApp.saveLimitingTarget(limiting);
            return result;
        }

        // 状态无效或已过期
        if (!dbAccount.checkStatus()) {
            result.fail(AccountError.INVALID_STATUS);
            facilityApp.saveLimitingTarget(limiting);
            return result;
        }

        // 密码是否正确
        boolean isLogin = dbAccount.checkPassword(input.getPassword());
        if (!isLogin) {
            result.fail(AccountError.INVALID_PASSWORD);
            facilityApp.saveLimitingTarget(limiting);
            return result;
        }

        // 用户是否存在
        Optional<Author> isAuthor = authorRep.selectById(dbAccount.getId());
        if (isAuthor.isEmpty()) {
            result.fail(TravellerError.INVALID_USER);
            facilityApp.saveLimitingTarget(limiting);
            return result;
        }

        Author author = isAuthor.get();
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
    public PageResult loadMany(AuthorListInput input, PageModel page) {
        List<AuthorListOutput> list = authorRep.selectMany(input, page);
        return PageResult.from(page, 1L, list);
    }
}
