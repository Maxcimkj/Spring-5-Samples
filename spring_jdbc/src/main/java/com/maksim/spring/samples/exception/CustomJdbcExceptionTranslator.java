package com.maksim.spring.samples.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.SQLException;

public class CustomJdbcExceptionTranslator extends SQLErrorCodeSQLExceptionTranslator {

    public CustomJdbcExceptionTranslator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
        return new CustomDataAccessException(sqlEx.getMessage(), sqlEx);
    }

    public static class CustomDataAccessException extends DataAccessException {
        public CustomDataAccessException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }
}
