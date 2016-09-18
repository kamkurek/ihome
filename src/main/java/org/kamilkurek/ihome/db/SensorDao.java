package org.kamilkurek.ihome.db;

import org.kamilkurek.ihome.models.Sensor;
import org.springframework.stereotype.Repository;

/**
 * Created by kamku on 03.07.2016.
 */
@Repository
public class SensorDao extends GenericObjectDao<Sensor> {

    @Override
    protected Class<Sensor> getGenericClass() {
        return Sensor.class;
    }

    public void synchronize() {

    }
}
