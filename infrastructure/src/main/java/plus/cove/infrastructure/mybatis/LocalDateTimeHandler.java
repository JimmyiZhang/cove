package plus.cove.infrastructure.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间格式处理
 * 适用于sqlite
 *
 * @param
 * @author jimmy.zhang
 * @return
 * @date 2019-09-06
 */
@MappedJdbcTypes(JdbcType.TIMESTAMP)
@MappedTypes(LocalDateTime.class)
public class LocalDateTimeHandler extends BaseTypeHandler<LocalDateTime> {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.format(FORMATTER));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String columnValue = rs.getString(columnName);
        return LocalDateTime.parse(columnValue, FORMATTER);
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return LocalDateTime.parse(columnValue, FORMATTER);
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return LocalDateTime.parse(columnValue, FORMATTER);
    }
}

