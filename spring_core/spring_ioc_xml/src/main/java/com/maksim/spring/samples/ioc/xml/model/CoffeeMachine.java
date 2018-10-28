package com.maksim.spring.samples.ioc.xml.model;

import java.util.Set;

public class CoffeeMachine {
    private CapsuleModule capsuleModule;
    private Heater heater;
    private MilkSupplier milkSupplier;
    private WaterSupplier waterSupplier;
    private Set<CoffeeTypeConfiguration> coffeeTypeConfigurations;
    private CoffeeBrewer coffeeBrewer;

    public CoffeeMachine(CapsuleModule capsuleModule, Heater heater, MilkSupplier milkSupplier,
                         WaterSupplier waterSupplier, Set<CoffeeTypeConfiguration> coffeeTypeConfiguration,
                         CoffeeBrewer coffeeBrewer) {
        this.capsuleModule = capsuleModule;
        this.heater = heater;
        this.milkSupplier = milkSupplier;
        this.waterSupplier = waterSupplier;
        this.coffeeTypeConfigurations = coffeeTypeConfiguration;
        this.coffeeBrewer = coffeeBrewer;
    }

    public void plugWater() {
        this.waterSupplier.plug();
    }

    public void fillMilk() {
        this.milkSupplier.fill();
    }

    public void setCapsuleModule(Object capsule) {
        this.capsuleModule.setCapsule(capsule);
    }

    public Object brewCoffee(CoffeeTypeConfiguration configuration) {
        if (!coffeeTypeConfigurations.contains(configuration))
            throw new RuntimeException("coffee type is not supported");

        Object capsule = capsuleModule.getCapsule();

        Object water = waterSupplier.getWater(configuration.getCountWater());
        Object hotWater = heater.heatWater(water);

        Object milk = milkSupplier.getMilk(configuration.getCountMilk());

        Object coffee = coffeeBrewer.brew(capsule, hotWater, milk);
        return coffee;
    }

    /**
     * For tests
     * @return
     */
    public CapsuleModule getCapsuleModule() {
        return capsuleModule;
    }

    public void setCapsuleModule(CapsuleModule capsuleModule) {
        this.capsuleModule = capsuleModule;
    }

    public Heater getHeater() {
        return heater;
    }

    public void setHeater(Heater heater) {
        this.heater = heater;
    }

    public MilkSupplier getMilkSupplier() {
        return milkSupplier;
    }

    public void setMilkSupplier(MilkSupplier milkSupplier) {
        this.milkSupplier = milkSupplier;
    }

    public WaterSupplier getWaterSupplier() {
        return waterSupplier;
    }

    public void setWaterSupplier(WaterSupplier waterSupplier) {
        this.waterSupplier = waterSupplier;
    }

    public Set<CoffeeTypeConfiguration> getCoffeeTypeConfigurations() {
        return coffeeTypeConfigurations;
    }

    public void setCoffeeTypeConfigurations(Set<CoffeeTypeConfiguration> coffeeTypeConfigurations) {
        this.coffeeTypeConfigurations = coffeeTypeConfigurations;
    }

    public CoffeeBrewer getCoffeeBrewer() {
        return coffeeBrewer;
    }

    public void setCoffeeBrewer(CoffeeBrewer coffeeBrewer) {
        this.coffeeBrewer = coffeeBrewer;
    }
}
