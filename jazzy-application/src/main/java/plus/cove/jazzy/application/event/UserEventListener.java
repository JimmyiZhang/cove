package plus.cove.jazzy.application.event;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import plus.cove.jazzy.domain.entity.account.UserEvent;

/**
 * 用户事件监听
 *
 * @author jimmy.zhang
 * @since 2.0
 */
@Component("userEventListener")
public class UserEventListener {
    @Async
    @TransactionalEventListener
    public void sendEmail(UserEvent event) {
        System.out.println("发送邮件，邮件地址：" + event.getUserMail());
    }
}
