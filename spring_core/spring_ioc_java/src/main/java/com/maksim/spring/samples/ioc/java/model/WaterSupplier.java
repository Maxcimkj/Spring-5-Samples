package com.maksim.spring.samples.ioc.java.model;

public class WaterSupplier {
    private boolean plugged;

    void plug() {
        this.plugged = true;
    }

    Object getWater(int waterCount) {
        if (!plugged)
            throw new RuntimeException("water doesn't plugged");

        return "water(count:" + waterCount + ")";
    }
}
