package org.kamilkurek.ihome;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * Created by kamil on 2016-06-05.
 */
public class Utils {

    public static String getCurrentDateString() {
        return getDateString(LocalDateTime.now());
    }

    public static String getDateString(LocalDateTime dateTime) {
        dateTime = dateTime.truncatedTo(ChronoUnit.SECONDS);
        return ISO_LOCAL_DATE_TIME.format(dateTime);
    }

}
