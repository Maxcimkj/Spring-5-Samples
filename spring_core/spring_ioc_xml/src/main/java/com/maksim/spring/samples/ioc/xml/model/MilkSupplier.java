package com.maksim.spring.samples.ioc.xml.model;

public class MilkSupplier {
    private boolean full;

    void fill() {
        this.full = true;
    }

    Object getMilk(int milkCount) {
        if (!full)
            throw new RuntimeException("milk is not poured");

        return "milk(count:" + milkCount + ")";
    }
}
