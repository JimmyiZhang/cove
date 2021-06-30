package plus.cove.jazzy.domain.entity.logging;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import plus.cove.jazzy.domain.principal.UserRequest;

/**
 * 用户事件监听
 *
 * @author jimmy.zhang
 * @since 2.0
 */
@Component("loggingEventListener")
public class LoggingEventListener {
    @Async
    @EventListener
    public void saveLog(UserRequest event) {
        LoggingBuilder builder = LoggingBuilder.create();
        builder.withRequest(event);
        Logging log = builder.toLogging();

        System.out.println("日志：" + log.toString());
    }
}
