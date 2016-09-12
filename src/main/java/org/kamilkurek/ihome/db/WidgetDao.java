package org.kamilkurek.ihome.db;

import org.kamilkurek.ihome.models.Widget;
import org.springframework.stereotype.Repository;

/**
 * Created by kamku on 03.07.2016.
 */
@Repository
public class WidgetDao extends GenericObjectDao<Widget> {

    @Override
    protected Class<Widget> getGenericClass() {
        return Widget.class;
    }

}
