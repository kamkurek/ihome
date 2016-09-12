package org.kamilkurek.ihome.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by kamil on 2016-09-12.
 */
public abstract class GenericObjectDao<T> {

    private JdbcTemplate jdbcTemplateObject;
    private Class<T> clazz = getGenericClass();

    public List<T> getAll() {
        String sql = "SELECT * FROM "+getModelName();
        List<T> all = jdbcTemplateObject.query(sql, new BeanPropertyRowMapper<>(clazz));
        return all;
    }

    protected abstract Class<T> getGenericClass();

    private String getModelName() {
        return clazz.getSimpleName();
    }

    @Autowired
    public final void setJdbcTemplateObject(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

}
