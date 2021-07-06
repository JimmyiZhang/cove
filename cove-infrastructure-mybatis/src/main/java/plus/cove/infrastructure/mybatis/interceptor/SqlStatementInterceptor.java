package plus.cove.infrastructure.mybatis.interceptor;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Properties;

/**
 * like通配符拦截器
 * 处理like注解中还有通配符的情况
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @since 1.0
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class SqlStatementInterceptor implements Interceptor {
    private static final String PARAM_NAME_PREFIX = "param";
    private static final String LIKE_ESCAPE_CHARACTERS = "%_[";
    private static final char LIKE_ESCAPE = '\\';

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        if (SqlCommandType.SELECT != ms.getSqlCommandType()) {
            return invocation.proceed();
        }

        Object param = args[1];
        if (param instanceof MapperMethod.ParamMap) {
            MapperMethod.ParamMap map = (MapperMethod.ParamMap) param;
            if (map == null || map.size() == 0) {
                return invocation.proceed();
            }

            for (Object mKey : map.keySet()) {
                Object origin = map.get(mKey);
                // 存在默认参数的对象，存在自己命名的参数就显示两个value，所以只查找一个即可
                if (origin == null || !mKey.toString().startsWith(PARAM_NAME_PREFIX)) {
                    continue;
                }
                // 查找SqlStatement注解
                SqlStatement statement = AnnotationUtils.findAnnotation(origin.getClass(), SqlStatement.class);
                if (statement == null) {
                    continue;
                }

                // 遍历参数类的字段
                Field[] fields = origin.getClass().getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    Field field = fields[j];

                    // 查找SqlLike注解，只支持String类型的参数
                    SqlLike sqlLike = AnnotationUtils.findAnnotation(field, SqlLike.class);
                    if (sqlLike != null && field.getType().equals(String.class)) {
                        field.setAccessible(true);
                        String before = (String) field.get(origin);
                        if (before == null || before == "") {
                            continue;
                        }

                        // 转义通配符
                        StringBuilder sbEscape = new StringBuilder(before.length() + LIKE_ESCAPE_CHARACTERS.length());
                        sbEscape.append(sqlLike.prefix());
                        for (int k = 0; k < before.length(); k++) {
                            char c = before.charAt(k);
                            // 是否存在需要转移的字符
                            for (int l = 0; l < LIKE_ESCAPE_CHARACTERS.length(); l++) {
                                if (LIKE_ESCAPE_CHARACTERS.charAt(l) == c) {
                                    sbEscape.append(LIKE_ESCAPE);
                                    break;
                                }
                            }
                            sbEscape.append(c);
                        }
                        sbEscape.append(sqlLike.suffix());
                        // 设置处理后的值
                        fields[j].set(origin, sbEscape.toString());
                        continue;
                    }

                    // 查找SqlDate注解，只支持LocalDate类型的参数
                    SqlDate sqlDate = AnnotationUtils.findAnnotation(field, SqlDate.class);
                    if (sqlDate != null && field.getType().equals(LocalDate.class)) {
                        field.setAccessible(true);
                          LocalDate before = (LocalDate) field.get(origin);
                        if (before == null) {
                            continue;
                        }

                        // 设置日期
                        field.set(origin, before.plus(sqlDate.increment(), sqlDate.unit()));
                        continue;
                    }
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        // to do nothing
    }
}
