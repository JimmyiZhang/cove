package plus.cove.infrastructure.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * json配置
 * 包括时间格式，Long类型转为String
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @date 2019-09-06
 */
@Configuration
@ConfigurationProperties("summer.json-config")
public class UniteJsonConfig {
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final ObjectMapper redisMapper = new ObjectMapper();

    public UniteJsonConfig() {
        setJsonMapper();
        setRedisMapper();
    }

    @Getter
    @Setter
    private String dateFormat = "yyyy-MM-dd";

    @Getter
    @Setter
    private String timeFormat = "HH:mm:ss";

    @Getter
    @Setter
    private String datetimeFormat = "yyyy-MM-dd HH:mm:ss";

    public ObjectMapper getJsonMapper() {
        return jsonMapper;
    }

    private void setJsonMapper() {
        // 反序列化忽略不存在的属性
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule simpleModule = new SimpleModule();
        // Long类型
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        // 日期类型
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(datetimeFormat);

        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(datetimeFormatter));

        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        simpleModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(datetimeFormatter));

        jsonMapper.disable(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS);
        jsonMapper.registerModule(simpleModule);
    }

    public ObjectMapper getRedisMapper() {
        return redisMapper;
    }

    private void setRedisMapper() {
        // 反序列化忽略不存在的属性
        redisMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        redisMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 设置携带类型名
        redisMapper.activateDefaultTyping(redisMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);

        SimpleModule simpleModule = new SimpleModule();
        // Long类型
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        // 日期类型
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(datetimeFormat);
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(datetimeFormatter));
        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        simpleModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(datetimeFormatter));

        redisMapper.disable(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS);
        redisMapper.registerModule(simpleModule);
    }
}

