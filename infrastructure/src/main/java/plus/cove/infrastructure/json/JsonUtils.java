package plus.cove.infrastructure.json;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * json工具类
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Slf4j
@Component
public class JsonUtils {
    @Autowired
    UniteJsonConfig config;

    /**
     * 反序列化
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public <T> T fromJson(String json, Class<T> clazz) {
        if (json == null || json.isEmpty() || clazz == null) {
            return null;
        }

        T result = null;
        try {
            if (clazz.equals(String.class)) {
                result = (T) json;
            } else {
                result = config.getJsonMapper().readValue(json, clazz);
            }
        } catch (Exception e) {
            log.warn("Parse Json to Object error", e);
        }

        return result;
    }

    /**
     * 序列化
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public <T> String toJson(T obj) {
        if (obj == null) {
            return null;
        }

        String result = null;
        try {
            if (obj instanceof String) {
                result = (String) obj;
            } else {
                result = config.getJsonMapper().writeValueAsString(obj);
            }
        } catch (Exception ex) {
            log.warn("Parse Object to Json error", ex);
        }

        return result;
    }
}
