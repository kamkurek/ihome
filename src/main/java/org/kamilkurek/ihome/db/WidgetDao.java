package org.kamilkurek.ihome.db;

import org.kamilkurek.ihome.models.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by kamku on 03.07.2016.
 */
@Repository
public class WidgetDao {

    private final JdbcTemplate jdbcTemplateObject;

    @Autowired
    public WidgetDao(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public List<Widget> getWidgets() {
        String sql = "SELECT id, name FROM widget";
        List<Widget> widgets = jdbcTemplateObject.query(sql, new BeanPropertyRowMapper<>(Widget.class));
        return widgets;
    }

}
