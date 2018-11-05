package com.maksim.spring.samples.model;

import java.util.Objects;

public class Officer {
    private int id;
    private String name;
    private String position;
    private int stationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Officer)) return false;
        Officer officer = (Officer) o;
        return id == officer.id &&
                stationId == officer.stationId &&
                Objects.equals(name, officer.name) &&
                Objects.equals(position, officer.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, position, stationId);
    }
}
