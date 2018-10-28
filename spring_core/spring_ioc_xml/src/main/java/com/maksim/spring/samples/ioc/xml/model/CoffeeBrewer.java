package com.maksim.spring.samples.ioc.xml.model;

public class CoffeeBrewer {
    private int pressure;

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public Object brew(Object coffeeCapsule, Object hotWater, Object milk) {
        return "coffee";
    }
}
