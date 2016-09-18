package org.kamilkurek.ihome.mvc.controllers;

import org.kamilkurek.ihome.db.DataDao;
import org.kamilkurek.ihome.db.SensorDao;
import org.kamilkurek.ihome.db.WidgetDao;
import org.kamilkurek.ihome.models.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by kamil on 2016-04-08.
 */
@Controller
@RequestMapping("/widget-settings")
public class WidgetSettingsController {

    private final SensorDao sensorDao;
    private final WidgetDao widgetDao;

    @Autowired
    public WidgetSettingsController(WidgetDao widgetDao, SensorDao sensorDao) {
        this.widgetDao = widgetDao;
        this.sensorDao = sensorDao;
    }

    @RequestMapping(method = GET)
    public String get(@RequestParam(required = false) Long id, Model model) {
        model.addAttribute("sensors", sensorDao.getAll());
        Widget widget;
        if(id != null) {
            widget = widgetDao.get(id);
        } else {
            widget = new Widget();
        }
        model.addAttribute("widget", widget);
        return "widget-settings";
    }

    @RequestMapping(method = POST)
    public String post(@RequestParam(required = false) Long id,
                       @RequestParam String name,
                       @RequestParam String color,
                       @RequestParam String sensor) {
        Widget widget = new Widget();
        widget.setId(id);
        widget.setName(name);
        widget.setColor(color);
        widget.setSensor(sensor);
        widgetDao.save(widget);
        return "redirect:/widget-settings?id="+widget.getId();
    }

}
