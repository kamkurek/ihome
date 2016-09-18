package org.kamilkurek.ihome;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by kamil on 2016-09-18.
 */
public class MyBeanPropertyRowMapper<T> extends BeanPropertyRowMapper<T> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public MyBeanPropertyRowMapper(Class<T> clazz) {
        super(clazz);
    }

    @Override
    protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
        Class<?> requiredType = pd.getPropertyType();
        if(LocalDateTime.class.equals(requiredType)) {
            String value = rs.getString(index);
            return LocalDateTime.parse(value, formatter);
        }
        return super.getColumnValue(rs, index, pd);
    }

}
