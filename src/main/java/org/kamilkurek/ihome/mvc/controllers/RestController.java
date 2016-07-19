package org.kamilkurek.ihome.mvc.controllers;

import org.kamilkurek.ihome.db.DataDao;
import org.kamilkurek.ihome.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Created by kamil on 2016-04-26.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final DataDao dataDao;

    @Autowired
    public RestController(DataDao dataDao) {
        this.dataDao = dataDao;
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public void post(
            @RequestParam(value="sensorId") String sensorId,
            @RequestParam(value="date", required = false) Optional<String> date,
            @RequestParam(value="value") String value
    ) {
        String dateString = date.orElse(Utils.getCurrentDateString());
        System.out.println(dateString+" "+value);
        dataDao.create(sensorId, dateString, value);
    }

}
