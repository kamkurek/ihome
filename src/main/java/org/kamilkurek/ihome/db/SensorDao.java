package org.kamilkurek.ihome.db;

import org.kamilkurek.ihome.models.Sensor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kamku on 03.07.2016.
 */
@Repository
public class SensorDao extends GenericObjectDao<Sensor> {

    @Override
    protected Class<Sensor> getGenericClass() {
        return Sensor.class;
    }

    public void save(Sensor sensor) {
        jdbcTemplateObject.update("DELETE FROM sensor WHERE id = "+sensor.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("id", sensor.getId());
        map.put("name", sensor.getName());
        new SimpleJdbcInsert(jdbcTemplateObject)
                .withTableName("sensor")
                .execute(map);
    }

}
