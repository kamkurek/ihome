package org.kamilkurek.ihome.mvc.controllers;

import org.kamilkurek.ihome.StaticSupplier;
import org.kamilkurek.ihome.db.DataDao;
import org.kamilkurek.ihome.db.WidgetParametersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.collections.MapUtils.safeAddToMap;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by kamil on 2016-04-08.
 */
@Controller
@RequestMapping("/widget-settings")
public class WidgetSettingsController {

    private final List<String> parameters;
    private final WidgetParametersDao widgetParametersDao;
    private final DataDao dataDao;

    @Autowired
    public WidgetSettingsController(StaticSupplier staticSupplier,
                                    WidgetParametersDao widgetParametersDao,
                                    DataDao dataDao) {
        this.parameters = staticSupplier.getWidgetParameters();
        this.widgetParametersDao = widgetParametersDao;
        this.dataDao = dataDao;
    }

    @RequestMapping(method = GET)
    public String get(@RequestParam(required = false) Long id, Model model) {
        addSensorsList(model);
        addCurrentSensor(model, id);
        addParameters(model, id);
        return "widget-settings";
    }

    private void addCurrentSensor(Model model, Long id) {
        if(id != null) {
            Map<String, String> widgetParams = widgetParametersDao.getWidgetParams(id);
            String currentSensor = widgetParams.get("sensor");
            model.addAttribute("currentSensor", currentSensor);
        }
    }

    private void addSensorsList(Model model) {
        model.addAttribute("sensors", dataDao.getSensorList());
    }

    private void addParameters(Model model, Long id) {
        Map<String, String> map = new HashMap<>(parameters.size());
        if(id != null) {
            Map<String, String> widgetParams = widgetParametersDao.getWidgetParams(id);
            parameters.forEach(p -> safeAddToMap(map, p, widgetParams.get(p)));
        } else {
            parameters.forEach(p -> map.put(p, ""));
        }
        model.addAttribute("parameters", map);

    }

    @RequestMapping(method = POST)
    public String post(@RequestParam Long id, @RequestParam String colors, @RequestParam String sensor) {
        widgetParametersDao.setWidgetParam(id, "colors", colors);
        widgetParametersDao.setWidgetParam(id, "sensor", sensor);
        return "redirect:/widget-settings?id="+id;
    }

}
