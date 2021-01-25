package plus.cove.infrastructure.exception;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

/**
 * 预校验工具
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@UtilityClass
public class Preconditions {
    /**
     * 真断言
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static void assertTrue(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException("invalid argument");
        }
    }

    /**
     * object不为null
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static <T> T assertNotNull(T object) {
        if (object == null) {
            throw new IllegalArgumentException("invalid argument");
        }
        return object;
    }

    /**
     * object为null
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static <T> void assertNull(T object) {
        if (Objects.nonNull(object)) {
            throw new IllegalArgumentException("invalid argument");
        }
    }

    /**
     * list不为null且不为空
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static <T> void assertNotEmpty(List<T> list) {
        if (Objects.isNull(list) || list.isEmpty()) {
            throw new IllegalArgumentException("invalid argument");
        }
    }

    /**
     * list为null或为空
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static <T> void assertEmpty(List<T> list) {
        if (Objects.nonNull(list) && !list.isEmpty()) {
            throw new IllegalArgumentException("invalid argument");
        }
    }

    /**
     * string不为null且不为空且不为空格
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static <T> void assertNotBlank(String string) {
        if (Objects.isNull(string) || string.trim().isEmpty()) {
            throw new IllegalArgumentException("invalid argument");
        }
    }

    /**
     * string为空格
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public static <T> void assertBlank(String string) {
        if (Objects.nonNull(string) && !string.trim().isEmpty()) {
            throw new IllegalArgumentException("invalid argument");
        }
    }
}
