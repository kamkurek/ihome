package org.kamilkurek.ihome.mvc.controllers;

import org.apache.commons.collections.MapUtils;
import org.kamilkurek.ihome.StaticSupplier;
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

    @Autowired
    public WidgetSettingsController(StaticSupplier staticSupplier,
                                    WidgetParametersDao widgetParametersDao) {
        this.parameters = staticSupplier.getWidgetParameters();
        this.widgetParametersDao = widgetParametersDao;
    }

    @RequestMapping(method = GET)
    public String get(@RequestParam(required = false) Long id, Model model) {
        Map<String, String> map = new HashMap<>(parameters.size());
        if(id != null) {
            Map<String, String> widgetParams = widgetParametersDao.getWidgetParams(id);
            parameters.forEach(p -> safeAddToMap(map, p, widgetParams.get(p)));
        } else {
            parameters.forEach(p -> map.put(p, ""));
        }
        model.addAttribute("parameters", map);
        return "widget-settings";
    }

    @RequestMapping(method = POST)
    public String post(@RequestParam String colors) {
        widgetParametersDao.setWidgetParam("1", "colors", colors);
        return "redirect:/widget-settings";
    }

}
