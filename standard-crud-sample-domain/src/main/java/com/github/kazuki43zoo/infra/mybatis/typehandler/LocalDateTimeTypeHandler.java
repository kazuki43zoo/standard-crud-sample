package com.github.kazuki43zoo.infra.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.LocalDateTime;

import java.sql.*;

public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, new Timestamp(parameter.toDate().getTime()));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return toDateTime(rs.getTimestamp(columnName));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return toDateTime(rs.getTimestamp(columnIndex));
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return toDateTime(cs.getTimestamp(columnIndex));
    }

    private LocalDateTime toDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            return new LocalDateTime(timestamp.getTime());
        }
    }

}
