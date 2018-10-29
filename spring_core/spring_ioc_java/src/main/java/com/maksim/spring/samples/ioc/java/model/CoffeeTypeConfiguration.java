package com.maksim.spring.samples.ioc.java.model;

import java.util.Objects;

public class CoffeeTypeConfiguration {
    private int temperature;
    private int pressure;
    private int countMilk;
    private int countWater;

    public CoffeeTypeConfiguration() {
    }

    public CoffeeTypeConfiguration(int temperature, int pressure, int countMilk, int countWater) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.countMilk = countMilk;
        this.countWater = countWater;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getCountMilk() {
        return countMilk;
    }

    public void setCountMilk(int countMilk) {
        this.countMilk = countMilk;
    }

    public int getCountWater() {
        return countWater;
    }

    public void setCountWater(int countWater) {
        this.countWater = countWater;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoffeeTypeConfiguration)) return false;
        CoffeeTypeConfiguration that = (CoffeeTypeConfiguration) o;
        return temperature == that.temperature &&
                pressure == that.pressure &&
                countMilk == that.countMilk &&
                countWater == that.countWater;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, pressure, countMilk, countWater);
    }
}
