package plus.cove.jazzy.application.impl;

import plus.cove.infrastructure.email.EmailUtils;
import plus.cove.jazzy.application.MailApplication;
import plus.cove.jazzy.domain.entity.account.ActivationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 邮件应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class MailApplicationImpl implements MailApplication {
    private EmailUtils mailUtils;
    private ActivationConfig activeConfig;

    @Autowired
    public MailApplicationImpl(EmailUtils utils,
                               ActivationConfig activeConfig) {
        this.activeConfig = activeConfig;
        this.mailUtils = utils;
    }

    @Override
    public void sendActivateMail(String email, String code) {
        String mailSubject = "发现世界注册激活邮件";
        String activeDays = activeConfig.getActiveDays().toString();
        String activeUrl = activeConfig.buildActiveUrl(code);


        StringBuilder mailContent = new StringBuilder()
                .append("您好：<br>")
                .append("欢迎您使用发现世界，您的账号尚未激活，请在<b>")
                .append(activeDays)
                .append("</b>天内激活账号，否则您的账号将无法使用。点击激活：<br><br>")
                .append("<a href='")
                .append(activeUrl)
                .append("'>")
                .append(activeUrl)
                .append("</a><br><br><br><br>")
                .append("有任何问题和需求请联系我，祝你使用愉快<br>")
                .append("遇见世界 发现生活<br>")
                .append("jimmy.zhang");

        // 发送邮件
        mailUtils.build()
                .to(email)
                .subject(mailSubject)
                .html(mailContent.toString())
                .send();
    }
}
