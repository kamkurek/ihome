package org.kamilkurek.ihome.mvc.controllers;

import org.kamilkurek.ihome.db.DataDao;
import org.kamilkurek.ihome.db.SensorDao;
import org.kamilkurek.ihome.models.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
    public String get(@RequestParam(required = false) Long id, Model model) {
        model.addAttribute("sensor", new Sensor());
        model.addAttribute("data", dataDao.getSensors());
        return "sensor-settings";
    }

//    @RequestMapping(method = POST)
//    public String post(@RequestParam(required = false) Long id,
//                       @RequestParam String name,
//                       @RequestParam String color,
//                       @RequestParam String sensor) {
//        Widget widget = new Widget();
//        widget.setId(id);
//        widget.setName(name);
//        widget.setColor(color);
//        widget.setSensor(sensor);
//        widgetDao.save(widget);
//        return "redirect:/widget-settings?id="+widget.getId();
//    }

}
