package com.carbybus.infrastructure.email;

import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.exception.NetworkError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
    private JavaMailSender sender;

    @Autowired
    MailProperties config;

    private ActionResult result;
    private MimeMessage mail;
    private MimeMessageHelper helper;

    public EmailUtils build() {
        result = ActionResult.OK;
        mail = sender.createMimeMessage();
        try {
            helper = new MimeMessageHelper(mail, true);
            helper.setFrom(config.getUsername());
        } catch (MessagingException ex) {
            log.error(NetworkError.MAIL_SEND_ERROR.getMessage(), ex);
            result.fail(NetworkError.MAIL_SEND_ERROR);
        }
        return this;
    }

    public EmailUtils to(String... toUsers) {
        try {
            helper.setTo(toUsers);
        } catch (MessagingException ex) {
            log.error(NetworkError.MAIL_TO_ERROR.getMessage(), ex);
            result.fail(NetworkError.MAIL_TO_ERROR);
        }
        return this;
    }

    public EmailUtils subject(String subject) {
        try {
            helper.setSubject(subject);
        } catch (MessagingException ex) {
            log.error(NetworkError.MAIL_SUBJECT_ERROR.getMessage(), ex);
            result.fail(NetworkError.MAIL_SUBJECT_ERROR);
        }
        return this;
    }

    public EmailUtils text(String text) {
        try {
            helper.setText(text);
        } catch (MessagingException ex) {
            log.error(NetworkError.MAIL_CONTENT_ERROR.getMessage(), ex);
            result.fail(NetworkError.MAIL_CONTENT_ERROR);
        }

        return this;
    }

    public EmailUtils html(String text) {
        try {
            helper.setText(text, true);
        } catch (MessagingException ex) {
            log.error(NetworkError.MAIL_CONTENT_ERROR.getMessage(), ex);
            result.fail(NetworkError.MAIL_CONTENT_ERROR);
        }

        return this;
    }

    public ActionResult send() {
        try {
            sender.send(mail);
        } catch (MailException ex) {
            log.error(NetworkError.MAIL_SEND_ERROR.getMessage(), ex);
            result.fail(NetworkError.MAIL_SEND_ERROR);
        }

        return result;
    }
}
