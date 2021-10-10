package plus.cove.jazzy.application.event;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import plus.cove.jazzy.domain.entity.logging.Logging;
import plus.cove.jazzy.domain.entity.logging.LoggingBuilder;
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
    @TransactionalEventListener
    public void saveLog(UserRequest event) {
        LoggingBuilder builder = LoggingBuilder.create();
        builder.withRequest(event);
        Logging log = builder.toLogging();

        System.out.println("日志：" + log.toString());
    }
}
