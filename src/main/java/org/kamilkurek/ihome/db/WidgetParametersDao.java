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

    public Map<String, String> getWidgetParams(String widgetId) {
        Map<String, String> map = new HashMap<>();
        String sql = "SELECT parameter, value " +
                "FROM widget_params " +
                "WHERE widget_id = ?";
        List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql, widgetId);
        maps.forEach(m -> map.put((String) m.get("parameter"), (String) m.get("value")));
        return map;
    }

}
