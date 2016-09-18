package org.kamilkurek.ihome.mvc.controllers;

import org.kamilkurek.ihome.db.DataDao;
import org.kamilkurek.ihome.db.SensorDao;
import org.kamilkurek.ihome.models.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by kamil on 2016-04-08.
 */
@Controller
@RequestMapping("/sensor-settings")
public class SensorSettingsController {

    private final DataDao dataDao;
    private final SensorDao sensorDao;

    @Autowired
    public SensorSettingsController(DataDao dataDao, SensorDao sensorDao) {
        this.dataDao = dataDao;
        this.sensorDao = sensorDao;
    }

    @RequestMapping(method = GET)
    public String get(Model model) {
        Map<String, Sensor> sensors = new HashMap<>();
        sensorDao.getAll().forEach(s -> sensors.put(s.getId(), s));

        model.addAttribute("sensors", sensors);
        model.addAttribute("data", dataDao.getSensors());
        return "sensor-settings";
    }

    @RequestMapping(method = POST)
    public String post(@RequestParam List<String> id,
            @RequestParam List<String> name) {
        for(int i = 0; i<id.size(); i ++) {
            Sensor sensor = new Sensor();
            sensor.setId(id.get(i));
            sensor.setName(name.get(i));
            sensorDao.save(sensor);
        }
        return "redirect:/sensor-settings";
    }

}
