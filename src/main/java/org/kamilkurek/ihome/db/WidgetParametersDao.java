package org.kamilkurek.ihome.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kamku on 03.07.2016.
 */
@Repository
public class WidgetParametersDao {

    private final JdbcTemplate jdbcTemplateObject;

    @Autowired
    public WidgetParametersDao(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public Map<String, String> getWidgetParams(long widgetId) {
        Map<String, String> map = new HashMap<>();
        String sql = "SELECT parameter, value " +
                "FROM widget_params " +
                "WHERE widget_id = ?";
        List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql, widgetId);
        maps.forEach(m -> map.put((String) m.get("parameter"), (String) m.get("value")));
        return map;
    }

    public void setWidgetParam(long widgetId, String param, String value) {
        String sql = "DELETE FROM widget_params " +
                "WHERE widget_id = ? " +
                "AND parameter = ?";

        jdbcTemplateObject.update(sql, widgetId, param);

        sql = "INSERT INTO widget_params (widget_id, parameter, value) VALUES (?, ?, ?)";

        jdbcTemplateObject.update(sql, widgetId, param, value);
    }
}
