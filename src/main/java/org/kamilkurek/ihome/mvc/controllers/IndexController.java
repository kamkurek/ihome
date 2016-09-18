package org.kamilkurek.ihome.mvc.controllers;

import org.kamilkurek.ihome.db.DataDao;
import org.kamilkurek.ihome.models.DataRow;
import org.kamilkurek.ihome.db.SensorDao;
import org.kamilkurek.ihome.db.WidgetDao;
import org.kamilkurek.ihome.models.Sensor;
import org.kamilkurek.ihome.models.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by kamil on 2016-04-08.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private final DataDao dataDao;
    private final WidgetDao widgetDao;
    private final SensorDao sensorDao;

    @Autowired
    public IndexController(DataDao dataDao, WidgetDao widgetDao, SensorDao sensorDao) {
        this.dataDao = dataDao;
        this.widgetDao = widgetDao;
        this.sensorDao = sensorDao;
    }

    @RequestMapping(method = GET) public String get(Model model, @RequestParam(defaultValue = "24") int hours) {
        List<Widget> widgets = widgetDao.getAll();
        List<Sensor> sensors = new ArrayList<>();
        widgets.forEach(widget -> sensors.add(sensorDao.get(widget.getSensor())));

        Map<String, String> latestDataMap = new HashMap<>(sensors.size());
        Map<String, List<DataRow>> dataMap = new HashMap<>(sensors.size());

        sensors.stream()
                .map(s -> s.getId())
                .forEach(uuid -> {
                    latestDataMap.put(uuid, dataDao.getLatestData(uuid).getValue());
                    dataMap.put(uuid, dataDao.getDataFromLastHours(uuid, hours));
                });

        model.addAttribute("interval", getInterval(hours));
        model.addAttribute("dataMap", dataMap);
        model.addAttribute("latestDataMap", latestDataMap);
        model.addAttribute("widgets", widgets);

        return "index";
    }

    private String getInterval(int hours) {
        if(hours % 24 == 0) {
            int days = hours/24;
            return days+(days>1?" days":" day");
        }
        return hours+(hours>1?" hours":" hour");
    }

}
