package org.kamilkurek.ihome.db;

import org.kamilkurek.ihome.MyBeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by kamil on 2016-09-12.
 */
public abstract class GenericObjectDao<T> {

    protected JdbcTemplate jdbcTemplateObject;
    private Class<T> clazz = getGenericClass();

    public List<T> getAll() {
        String sql = "SELECT * FROM "+getModelName();
        List<T> all = jdbcTemplateObject.query(sql, new MyBeanPropertyRowMapper<>(clazz));
        return all;
    }

    public void delete(long id) {
        jdbcTemplateObject.update("DELETE FROM "+getModelName()+" WHERE id = ?", id);
    }

    protected abstract Class<T> getGenericClass();

    private String getModelName() {
        return clazz.getSimpleName();
    }

    @Autowired
    public final void setJdbcTemplateObject(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public T get(Long id) {
        String sql = "SELECT * FROM "+getModelName()+" WHERE id=?";
        T t = jdbcTemplateObject.queryForObject(sql, new MyBeanPropertyRowMapper<>(clazz), id);
        return t;
    }

    public T get(String id) {
        String sql = "SELECT * FROM "+getModelName()+" WHERE id=?";
        T t = jdbcTemplateObject.queryForObject(sql, new MyBeanPropertyRowMapper<>(clazz), id);
        return t;
    }
}
