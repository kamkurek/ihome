package org.kamilkurek.ihome.mvc.controllers;

import org.kamilkurek.ihome.db.WidgetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by kamil on 2016-04-08.
 */
@Controller
@RequestMapping("/widget-delete")
public class WidgetDeleteController {

    private final WidgetDao widgetDao;

    @Autowired
    public WidgetDeleteController(WidgetDao widgetDao) {
        this.widgetDao = widgetDao;
    }

    @RequestMapping(method = POST)
    public String post(@RequestParam Long id) {
        widgetDao.delete(id);
        return "redirect:/settings";
    }

}
