package org.kamilkurek.ihome.models;

import java.time.LocalDateTime;

/**
 * Created by kamil on 2016-09-18.
 */
public class Data {

    private String id;
    private Integer numberOfRows;
    private LocalDateTime latestData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public LocalDateTime getLatestData() {
        return latestData;
    }

    public void setLatestData(LocalDateTime latestData) {
        this.latestData = latestData;
    }
}
