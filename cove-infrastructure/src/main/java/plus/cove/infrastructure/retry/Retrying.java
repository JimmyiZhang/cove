package plus.cove.infrastructure.retry;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;

import java.util.function.Supplier;

/**
 * 重试
 * <p>
 * 基于Spring retry进行包装
 *
 * @author jimmy.zhang
 * @since 2.0
 */
@Slf4j
public class Retrying {
    /**
     * 重试模板
     */
    @Getter
    @Setter
    private RetryTemplate template;

    /**
     * 执行重试
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public void execute(Supplier<Boolean> supplier) {
        template.execute(
                retry -> {
                    boolean result = supplier.get();
                    log.debug("[cove.infrastructure.retrying] retry task result: {}", result);

                    // 执行失败，抛出异常，执行重试
                    if (result == false) {
                        throw new RetryingException();
                    }
                    return true;
                },
                back -> {
                    log.warn("[cove.infrastructure.retrying] retry task failure");
                    return false;
                }
        );
    }
}
