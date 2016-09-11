package org.kamilkurek.ihome.mvc.controllers;

import org.kamilkurek.ihome.db.WidgetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by kamil on 2016-04-08.
 */
@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final WidgetDao widgetDao;

    @Autowired
    public SettingsController(WidgetDao widgetDao) {
        this.widgetDao = widgetDao;
    }

    @RequestMapping(method = GET) public String get(Model model) {
        model.addAttribute("widgets", widgetDao.getWidgets());
        return "settings";
    }

}
