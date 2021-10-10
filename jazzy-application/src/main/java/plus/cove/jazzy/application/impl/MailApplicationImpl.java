package plus.cove.jazzy.application.impl;

import plus.cove.infrastructure.utils.EmailHelper;
import plus.cove.jazzy.application.MailApplication;
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
    private EmailHelper mailUtils;

    @Autowired
    public MailApplicationImpl(EmailHelper utils) {
        this.mailUtils = utils;
    }

    @Override
    public void sendValidateMail(String email, String code) {
        String mailSubject = "发现世界-验证码";

        StringBuilder mailContent = new StringBuilder()
                .append("您好：<br><br>")
                .append("欢迎您使用发现世界，您本次验证码是：")
                .append("<br><br><br><br>")
                .append(code)
                .append("<br><br><br><br>")
                .append("验证码10分钟内有效<br>")
                .append("==遇见世界 发现生活==<br>")
                .append("https://github.com/JimmyiZhang/cove");

        // 发送邮件
        mailUtils.build()
                .to(email)
                .subject(mailSubject)
                .html(mailContent.toString())
                .send();
    }

    @Override
    public void sendSignupMail(String email, String name) {
        String mailSubject = "发现世界-注册成功";

        StringBuilder mailContent = new StringBuilder()
                .append(name)
                .append("，您好：<br><br>")
                .append("欢迎您使用发现世界，您的账号已激活，可以正常使用。")
                .append("<br><br><br><br>")
                .append("祝您使用愉快，有任何问题请联系jimmyi.zhang@outlook.com<br>")
                .append("==遇见世界 发现生活==<br>")
                .append("https://github.com/JimmyiZhang/cove");

        // 发送邮件
        mailUtils.build()
                .to(email)
                .subject(mailSubject)
                .html(mailContent.toString())
                .send();
    }
}
