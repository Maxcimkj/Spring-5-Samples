package com.maksim.spring.samples.ioc.xml.model;

public class Heater {
    int temperature;

    Object heatWater(Object water) {
        return "heat_water";
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
