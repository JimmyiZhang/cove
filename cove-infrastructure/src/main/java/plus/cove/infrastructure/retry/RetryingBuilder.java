package plus.cove.infrastructure.retry;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.*;
import org.springframework.retry.policy.*;
import org.springframework.retry.support.RetryTemplate;

import java.util.*;

/**
 * 重试构造器
 *
 * @author jimmy.zhang
 * @since 2.0
 */
public class RetryingBuilder {
    /**
     * 重试策略
     */
    private RetryPolicy retryPolicy;

    /**
     * 回退策略
     */
    private BackOffPolicy backPolicy;

    private RetryingBuilder() {
    }

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static RetryingBuilder create() {
        RetryingBuilder builder = new RetryingBuilder();
        return builder;
    }

    /**
     * 永不重试
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public RetryingBuilder withRetryNever() {
        this.retryPolicy = new NeverRetryPolicy();
        return this;
    }

    /**
     * 重试次数
     * <p>
     * 重试maxRetryTimes后返回
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public RetryingBuilder withRetryTimes(int maxRetryTimes) {
        Map<Class<? extends Throwable>, Boolean> exceptions = new HashMap<>(1);
        exceptions.put(RetryingException.class, true);

        SimpleRetryPolicy policy = new SimpleRetryPolicy(maxRetryTimes, exceptions);
        this.retryPolicy = policy;
        return this;
    }

    /**
     * 重试时间
     * <p>
     * 重试时间fixedRetryTime过后返回
     *
     * @param fixedRetryTime 重试时间，单位毫秒
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public RetryingBuilder withRetryTime(long fixedRetryTime) {
        CompositeRetryPolicy policy = new CompositeRetryPolicy();

        // 超时策略
        TimeoutRetryPolicy timPolicy = new TimeoutRetryPolicy();
        timPolicy.setTimeout(fixedRetryTime);

        // 异常策略
        BinaryExceptionClassifierRetryPolicy excPolicy = new BinaryExceptionClassifierRetryPolicy(
                BinaryExceptionClassifier.builder().retryOn(RetryingException.class).traversingCauses().build()
        );

        RetryPolicy[] policies = ArrayUtils.toArray(timPolicy, excPolicy);
        policy.setPolicies(policies);
        this.retryPolicy = policy;
        return this;
    }

    /**
     * 无退避策略
     * 每次重试时立即重试
     *
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public RetryingBuilder withBackNever() {
        this.backPolicy = new NoBackOffPolicy();
        return this;
    }

    /**
     * 固定回退时间
     *
     * @param fixedBackPeriod: 回退时间，单位毫秒
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public RetryingBuilder withBackTime(long fixedBackPeriod) {
        FixedBackOffPolicy policy = new FixedBackOffPolicy();
        policy.setBackOffPeriod(fixedBackPeriod);
        this.backPolicy = policy;
        return this;
    }

    /**
     * 随机回退时间
     *
     * @param minBackPeriod: 最小回退时间，单位毫秒
     * @param maxBackPeriod: 最大回退时间，单位毫秒
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public RetryingBuilder withBackRandom(long minBackPeriod, long maxBackPeriod) {
        UniformRandomBackOffPolicy policy = new UniformRandomBackOffPolicy();
        policy.setMinBackOffPeriod(minBackPeriod);
        policy.setMaxBackOffPeriod(maxBackPeriod);
        this.backPolicy = policy;
        return this;
    }

    /**
     * 指数回退时间
     *
     * @param initialInterval 初始睡眠间隔值，大于1
     * @param maxInterval     最大睡眠值，大于1
     * @param multiplier      乘数值，大于1.0
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public RetryingBuilder withBackExponential(long initialInterval, long maxInterval, double multiplier) {
        ExponentialBackOffPolicy policy = new ExponentialBackOffPolicy();
        policy.setInitialInterval(initialInterval);
        policy.setMaxInterval(maxInterval);
        policy.setMultiplier(multiplier);
        this.backPolicy = policy;
        return this;
    }

    /**
     * 构建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public Retrying build() {
        RetryTemplate template = new RetryTemplate();

        // 重试策略
        if (this.retryPolicy == null) {
            this.retryPolicy = new SimpleRetryPolicy(3);
        }
        template.setRetryPolicy(this.retryPolicy);

        // 回退策略
        if (this.backPolicy == null) {
            this.backPolicy = new FixedBackOffPolicy();
        }
        template.setBackOffPolicy(this.backPolicy);

        Retrying retry = new Retrying();
        retry.setTemplate(template);

        return retry;
    }
}
