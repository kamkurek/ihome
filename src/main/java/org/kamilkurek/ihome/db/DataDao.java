package org.kamilkurek.ihome.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by kamil on 2016-06-05.
 */

@Repository
public class DataDao {

    private final JdbcTemplate jdbcTemplateObject;

    @Autowired
    public DataDao(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(String sensorId, String date, String value) {
        String sql = "insert into data (sensor_id, date, value) values (?, ?, ?)";
        jdbcTemplateObject.update(sql, sensorId, date, value);
    }

    public Collection<String> getSensorList() {
        String sql = "SELECT DISTINCT sensor_id FROM data";
        List<String> query = jdbcTemplateObject.query(sql, new SingleColumnRowMapper(String.class));
        return query;
    }

    public List<DataRow> getDataFromLastHours(String sensorId, int hours) {
        String sql ="SELECT date, value " +
                    "FROM data " +
                    "WHERE sensor_id = ?" +
                    "AND datetime(date) >= datetime('now','localtime','-%s hour')";
        sql = String.format(sql, hours);

        return jdbcTemplateObject.query(sql, new DataRowMapper(), sensorId);
    }

    public DataRow getLatestData(String sensorId) {
        String sql = "SELECT date, value " +
                     "FROM data " +
                     "WHERE sensor_id = ?" +
                     "ORDER BY datetime(date) DESC " +
                     "LIMIT 1";
        return jdbcTemplateObject.queryForObject(sql, new DataRowMapper(), sensorId);
    }

    public Optional<String> getSensorName(String sensorId) {
        String sql = "SELECT name FROM sensors WHERE id = ?";

        return jdbcTemplateObject.query(sql, new SingleColumnRowMapper(String.class), sensorId).stream().findFirst();
    }

    private static final class DataRowMapper implements RowMapper<DataRow> {
        public DataRow mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DataRow(rs.getString("value"), rs.getString("date"));
        }
    }


}
