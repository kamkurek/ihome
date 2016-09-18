package org.kamilkurek.ihome.db;

import org.kamilkurek.ihome.models.Widget;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kamku on 03.07.2016.
 */
@Repository
public class WidgetDao extends GenericObjectDao<Widget> {

    @Override
    protected Class<Widget> getGenericClass() {
        return Widget.class;
    }

    private Long create() {
        Map<String, Object> map = new HashMap<>();
        return new SimpleJdbcInsert(jdbcTemplateObject)
                .withTableName("widget")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(map)
                .longValue();
    }

    public void save(Widget widget) {
        if(widget.getId() == null) {
            widget.setId(create());
        }
        String sql = "UPDATE widget SET " +
                " name = ? " +
                ",color = ? " +
                ",sensor = ? " +
                ",type = ?" +
                "WHERE id = ?";
        jdbcTemplateObject.update(sql,
                 widget.getName()
                ,widget.getColor()
                ,widget.getSensor()
                ,widget.getType()
                ,widget.getId());
    }
}
