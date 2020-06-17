package plus.cove.infrastructure.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import plus.cove.infrastructure.component.BaseEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 枚举处理器
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @date since 1.0
 */
public class BaseEnumHandler<E extends Enum<?> & BaseEnum> extends BaseTypeHandler<BaseEnum> {
    private Class<E> type;

    public BaseEnumHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return rs.wasNull() ? null : valueOf(code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return rs.wasNull() ? null : valueOf(code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return cs.wasNull() ? null : valueOf(code);
    }

    private E valueOf(int value) {
        try {
            return BaseEnumUtils.valueOf(type, value);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + value + " to " + type.getSimpleName() + " by value.", ex);
        }
    }
}