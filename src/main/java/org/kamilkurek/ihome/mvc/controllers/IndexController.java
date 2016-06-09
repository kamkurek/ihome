package org.kamilkurek.ihome.mvc.controllers;

import org.kamilkurek.ihome.DataDao;
import org.kamilkurek.ihome.DataRow;
import org.springframework.beans.factory.annotation.Required;
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

    private DataDao dataDao;

    @RequestMapping(method = GET) public String get(Model model, @RequestParam(defaultValue = "48") int hours) {
        Collection<String> sensorList = dataDao.getSensorList();

        Map<String, String> latestDataMap = new HashMap<>(sensorList.size());
        Map<String, List<DataRow>> dataMap = new HashMap<>(sensorList.size());
        Map<String, String> sensorNames = new HashMap<>(sensorList.size());

        sensorList.forEach(uuid -> latestDataMap.put(uuid, dataDao.getLatestData(uuid).getValue()));
        sensorList.forEach(uuid -> dataMap.put(uuid, dataDao.getDataFromLastHours(uuid, hours)));
        sensorList.forEach(uuid -> sensorNames.put(uuid, dataDao.getSensorName(uuid).orElse(uuid)));

        model.addAttribute("interval",getInterval(hours));
        model.addAttribute("dataMap", dataMap);
        model.addAttribute("latestDataMap", latestDataMap);
        model.addAttribute("sensorNames", sensorNames);

        return "index";
    }

    private String getInterval(int hours) {
        if(hours % 24 == 0) {
            int days = hours/24;
            return days+(days>1?" days":" day");
        }
        return hours+(hours>1?" hours":" hour");
    }

    @Required
    public void setDataDao(DataDao dataDao) {
        this.dataDao = dataDao;
    }
}
