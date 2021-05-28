package plus.cove.jazzy.domain.entity.user;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 用户事件监听
 *
 * @author jimmy.zhang
 * @since 2.0
 */
@Component("userEventListener")
public class UserEventListener {
    @Async
    @EventListener
    public void sendEmail(UserEvent event) {
        System.out.println("发送邮件，邮件地址：" + event.getUserMail());
    }
}
