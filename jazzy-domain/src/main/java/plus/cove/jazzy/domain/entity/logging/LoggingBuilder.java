package plus.cove.jazzy.domain.entity.logging;

import org.springframework.util.StringUtils;
import plus.cove.infrastructure.component.BaseEnum;
import plus.cove.jazzy.domain.principal.UserRequest;

import java.util.List;
import java.util.function.Function;

/**
 * 日志建造者
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public class LoggingBuilder {
    private String separate_item = "，";
    private String separate_list = "、";
    private StringBuilder builder;

    protected Logging log;

    public LoggingBuilder() {
        this.log = new Logging();
    }

    public LoggingBuilder withBrief(String brief) {
        this.log.setBrief(brief);
        return this;
    }

    public LoggingBuilder withCategory(String category) {
        this.log.setCategory(category);
        return this;
    }

    public LoggingBuilder withRequest(UserRequest request) {
        if (this.log.getOperateId() == null && request.getUserId() != null) {
            this.log.setOperateId(request.getUserId());
            this.log.setOperateName(request.getUserName());
        }

        this.log.setRouter(request.getRouter());
        this.log.setOperateSource(request.getSource());
        this.log.setOperateVersion(request.getVersion());
        return this;
    }

    public Logging toLogging() {
        this.log.setDetail(this.builder.toString());
        if (StringUtils.isEmpty(this.log.getBrief())) {
            this.log.setBrief(this.log.getDetail());
        }
        return this.log;
    }

    public static LoggingBuilder create() {
        LoggingBuilder lb = new LoggingBuilder();
        lb.builder = new StringBuilder(16);
        return lb;
    }

    public static LoggingBuilder create(int capacity, String itemSeparator, String listSeparator) {
        LoggingBuilder lb = new LoggingBuilder();
        lb.separate_item = itemSeparator;
        lb.separate_list = listSeparator;
        lb.builder = new StringBuilder(capacity);
        return lb;
    }

    /**
     * 添加
     * obj为null或empty则不添加
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public LoggingBuilder append(String obj) {
        if (obj == null || obj.equals("")) {
            return this;
        }

        builder.append(obj);
        return this;
    }

    /**
     * 添加
     * predict为true, 则添加
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public LoggingBuilder append(boolean predict, String obj) {
        if (predict) {
            this.append(obj);
        }
        return this;
    }

    /**
     * 添加一项，带分割符
     * obj为null或empty则不添加
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public LoggingBuilder appendItem(String obj) {
        if (obj == null || obj.equals("")) {
            return this;
        }

        if (this.builder.length() > 0) {
            builder.append(separate_item);
        }
        builder.append(obj);
        return this;
    }

    /**
     * 添加一项，带分割符
     * predict为true, 则添加
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public LoggingBuilder appendItem(boolean predict, String obj) {
        if (predict) {
            this.appendItem(obj);
        }
        return this;
    }

    /**
     * 添加一项，可格式化
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public LoggingBuilder appendItem(String format, Object obj) {
        if (obj == null) {
            return this;
        }

        String value = "";
        if (obj instanceof BaseEnum) {
            value = String.format(format, ((BaseEnum) obj).getDesc());
        }

        if (obj instanceof String) {
            if ("".equals(obj)) {
                return this;
            }
            value = String.format(format, obj);
        }

        if (obj instanceof List) {
            value = String.format(format, String.join(separate_list, (List) obj));
        }

        if (this.builder.length() > 0) {
            builder.append(separate_item);
        }
        builder.append(value);
        return this;
    }

    /**
     * 添加一项
     * 支持集合，自动格式化
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public <T> LoggingBuilder appendItem(String format, List<T> source, Function<T, String> map) {
        if (source == null) {
            return this;
        }

        StringBuilder sbValue = new StringBuilder(source.size() * 2);
        for (int i = 0; i < source.size(); i++) {
            if (i > 0) {
                sbValue.append(separate_list);
            }
            T src = source.get(i);
            sbValue.append(map.apply(src));
        }

        if (this.builder.length() > 0) {
            builder.append(separate_item);
        }

        String value = String.format(format, sbValue);
        return this;
    }
}
