package com.carbybus.infrastructure.email;

import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.exception.NetworkError;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;

/**
 * 发送邮件工具类
 *
 * @author jimmy.zhang
 * @date 2019-06-19
 */
@Slf4j
@Component
public class EmailUtils {
    @Autowired
    private UniteEmailConfig emailConfig;

    private Email email;
    private ActionResult result;

    private EmailUtils() {
        emailConfig.check();

        this.email = new SimpleEmail();
        email.setHostName(emailConfig.getHostName());
        email.setSmtpPort(emailConfig.getHostPort());
        email.setAuthentication(emailConfig.getUserName(), emailConfig.getUserPassword());
        email.setSSLOnConnect(emailConfig.getSslConnect());

        result = ActionResult.OK;
    }

    public static EmailUtils create(){
        return new EmailUtils();
    }

    public EmailUtils to(final String... emails) {
        try {
            this.email.addTo(emails);
        } catch (EmailException ex) {
            log.error(NetworkError.MAIL_TO_ERROR.getMessage(), ex);
            result.fail(NetworkError.MAIL_TO_ERROR);
        }

        return this;
    }

    public EmailUtils from(String email) {
        try {
            this.email.setFrom(email);
        } catch (EmailException ex) {
            log.error(NetworkError.MAIL_TO_ERROR.getMessage(), ex);
            result.fail(NetworkError.MAIL_TO_ERROR);
        }
        return this;
    }

    public EmailUtils subject(String subject) {
        this.email.setSubject(subject);
        return this;
    }

    public EmailUtils message(String message) {
        try {
            this.email.setMsg(message);
        } catch (EmailException ex) {
            log.error(NetworkError.MAIL_CONTENT_ERROR.getMessage(), ex);
            result.fail(NetworkError.MAIL_CONTENT_ERROR);
        }
        return this;
    }

    public ActionResult send() {
        try {
            InternetAddress fromAddress = this.email.getFromAddress();
            if (fromAddress == null) {
                this.email.setFrom(emailConfig.getFromUser());
            }
            String messageID = this.email.send();
            result.succeed(messageID);
        } catch (EmailException ex) {
            log.error(NetworkError.MAIL_SEND_ERROR.getMessage(), ex);
            result.fail(NetworkError.MAIL_SEND_ERROR);
        }

        return result;
    }
}
