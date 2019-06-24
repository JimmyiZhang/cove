package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.MailApplication;
import com.carbybus.cove.domain.entity.user.AccountActivationConfig;
import com.carbybus.infrastructure.email.EmailUtils;
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
    private AccountActivationConfig activeConfig;

    @Autowired
    public MailApplicationImpl(EmailUtils utils) {
        this.mailUtils = utils;
    }

    @Override
    public void sendActivateMail(String email, String code) {
        String mailSubject = "发现世界注册激活邮件";
        String activeDays = activeConfig.getActiveDays().toString();
        String activeUrl = activeConfig.buildActiveUrl(code);

        StringBuilder mailContent = new StringBuilder()
                .append("您好：<br>")
                .append("欢迎您使用发现世界，您的账号尚未激活，请在 ")
                .append(activeDays)
                .append(" 天内激活账号，否则您的账号将无法使用。<br>")
                .append("<br>")
                .append("激活地址如下：")
                .append(activeUrl)
                .append("<br>")
                .append("有任何问题和需求请联系我，祝你使用愉快<br>")
                .append("遇见世界 发现生活");

        // 发送邮件
        mailUtils.build()
                .to(email)
                .subject(mailSubject)
                .html(mailContent.toString())
                .send();
    }
}
