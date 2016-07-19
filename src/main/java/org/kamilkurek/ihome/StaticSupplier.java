package org.kamilkurek.ihome;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by kamku on 03.07.2016.
 */
@Component
public class StaticSupplier {

    private static final String[] WIDGET_PARAMETERS_ARRAY = new String[] { "colors" };
    private static final List<String> WIDGET_PARAMETERS = Collections.unmodifiableList(Arrays.asList(WIDGET_PARAMETERS_ARRAY));

    public List<String> getWidgetParameters() {
        return WIDGET_PARAMETERS;
    }

}
