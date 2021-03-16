package plus.cove.infrastructure.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.exception.NetworkError;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件工具类
 *
 * @author jimmy.zhang
 * @date 2019-06-19
 */
@Component
public class EmailUtils {
    private final Logger log = LoggerFactory.getLogger(EmailUtils.class);

    @Autowired
    JavaMailSender sender;

    @Autowired
    MailProperties config;

    private ActionResult result;
    private MimeMessage mail;
    private MimeMessageHelper helper;

    public EmailUtils build() {
        result = ActionResult.success();
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
