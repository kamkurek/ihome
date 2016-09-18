package org.kamilkurek.ihome.mvc.controllers;

import org.kamilkurek.ihome.db.DataDao;
import org.kamilkurek.ihome.db.DataRow;
import org.kamilkurek.ihome.db.WidgetDao;
import org.kamilkurek.ihome.models.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by kamil on 2016-04-08.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private final DataDao dataDao;
    private final WidgetDao widgetDao;

    @Autowired
    public IndexController(DataDao dataDao, WidgetDao widgetDao) {
        this.dataDao = dataDao;
        this.widgetDao = widgetDao;
    }

    @RequestMapping(method = GET) public String get(Model model, @RequestParam(defaultValue = "24") int hours) {
        Collection<String> sensorList = dataDao.getSensorList();

        Map<String, String> latestDataMap = new HashMap<>(sensorList.size());
        Map<String, List<DataRow>> dataMap = new HashMap<>(sensorList.size());
        Map<String, String> sensorNames = new HashMap<>(sensorList.size());

        sensorList.forEach(uuid -> {
            latestDataMap.put(uuid, dataDao.getLatestData(uuid).getValue());
            dataMap.put(uuid, dataDao.getDataFromLastHours(uuid, hours));
            sensorNames.put(uuid, dataDao.getSensorName(uuid).orElse(uuid));
        });

        List<Widget> widgets = widgetDao.getAll();
        Map<Long, Widget> widgetsMap = new HashMap<>(widgets.size());
        widgets.forEach(widget -> widgetsMap.put(widget.getId(), widget));

        model.addAttribute("interval", getInterval(hours));
        model.addAttribute("dataMap", dataMap);
        model.addAttribute("latestDataMap", latestDataMap);
        model.addAttribute("sensorNames", sensorNames);
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
