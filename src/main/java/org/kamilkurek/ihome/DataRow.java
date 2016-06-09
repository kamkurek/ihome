package org.kamilkurek.ihome;

/**
 * Created by kamil on 2016-06-06.
 */
public class DataRow {

    private String date;
    private String value;

    public DataRow(String value, String date) {
        this.value = value;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DataRow{" +
                "date='" + date + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
