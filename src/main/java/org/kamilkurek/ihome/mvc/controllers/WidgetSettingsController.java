package org.kamilkurek.ihome.mvc.controllers;

import org.kamilkurek.ihome.StaticSupplier;
import org.kamilkurek.ihome.db.WidgetParametersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String get(Model model) {
        Map<String, String> map = new HashMap<>(parameters.size());
        Map<String, String> widgetParams = widgetParametersDao.getWidgetParams("1");
        parameters.forEach(p -> map.put(p, widgetParams.get(p)));
        model.addAttribute("parameters", map);
        return "widget-settings";
    }

    @RequestMapping(method = POST)
    public String post(@ModelAttribute String s) {
        System.out.println(s);
        return "redirect:/widget-settings";
    }

}
