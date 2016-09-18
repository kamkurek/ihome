package org.kamilkurek.ihome.models;

/**
 * Created by kamku on 2016-09-11.
 */
public class Widget {

    private Long id;
    private String name;
    private String color;
    private String sensor;
    private WidgetType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public WidgetType getType() {
        return type;
    }

    public void setType(WidgetType type) {
        this.type = type;
    }

    public enum WidgetType {
        CHART, BUTTON
    }
}
