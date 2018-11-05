package com.maksim.spring.samples.dao;

import com.maksim.spring.samples.model.Station;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTemplateOnlyDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateOnlyDao(DataSource dataSource, SQLErrorCodeSQLExceptionTranslator exceptionTranslator) {
        this.jdbcTemplate = new JdbcTemplate();
        this.jdbcTemplate.setDataSource(dataSource);
        this.jdbcTemplate.setExceptionTranslator(exceptionTranslator);
    }

    public List<String> getStationNames() {
        return jdbcTemplate.queryForList("SELECT name FROM Station", String.class);
    }

    public void insertStation(Station station) {
        jdbcTemplate.update("INSERT INTO Station(name, line) VALUES (?, ?)",
                station.getName(), station.getLine());
    }

    public void deleteStationByName(String name) {
        jdbcTemplate.update("DELETE FROM Station WHERE name = ?", name);
    }
}
