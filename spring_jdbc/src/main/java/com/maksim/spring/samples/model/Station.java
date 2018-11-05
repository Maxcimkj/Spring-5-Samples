package com.maksim.spring.samples.model;

import java.util.List;
import java.util.Objects;

public class Station {
    private int id;
    private String name;
    private String line;
    private List<Officer> officers;

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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public List<Officer> getOfficers() {
        return officers;
    }

    public void setOfficers(List<Officer> officers) {
        this.officers = officers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;
        Station station = (Station) o;
        return id == station.id &&
                Objects.equals(name, station.name) &&
                Objects.equals(line, station.line) &&
                Objects.equals(officers, station.officers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, line, officers);
    }
}
