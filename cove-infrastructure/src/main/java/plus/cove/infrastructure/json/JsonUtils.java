package plus.cove.infrastructure.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * json工具类
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Component
public class JsonUtils {
    private final Logger log = LoggerFactory.getLogger(JsonUtils.class);

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
    public String fromJson(String json, String path) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        String result = "";
        String[] paths = path.split("\\.");
        try {
            JsonNode node = config.getJsonMapper().readTree(json);
            for (String pt : paths) {
                node = node.path(pt);
            }
            result = node.asText();
        } catch (Exception e) {
            log.warn("Parse Json to Object error", e);
        }

        return result;
    }

    /**
     * 反序列化
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public <T> T fromObject(String json, Class<T> clazz) {
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
     * 反序列化集合
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public <T> List<T> fromArray(String json, Class<T> clazz) {
        if (json == null || json.isEmpty() || clazz == null) {
            return null;
        }

        List<T> result = null;
        try {
            ObjectMapper mapper = config.getJsonMapper();
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            result = mapper.readValue(json, listType);
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
