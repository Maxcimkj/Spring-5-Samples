package com.maksim.spring.samples.ioc.java;

import com.maksim.spring.samples.ioc.java.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.util.*;

@Configuration
@PropertySource("classpath:configurations.properties")
public class SpringContextConfiguration {
    @Bean
    @Scope("prototype")
    public CapsuleModule capsuleModule() {
        return new CapsuleModule();
    }

    @Bean
    @Scope("prototype")
    public CoffeeBrewer coffeeBrewer() {
        return new CoffeeBrewer();
    }

    @Bean
    @Scope("prototype")
    public Heater heater() {
        return new Heater();
    }

    @Bean
    @Scope("prototype")
    public MilkSupplier milkSupplier() {
        return new MilkSupplier();
    }

    @Bean
    @Scope("prototype")
    public WaterSupplier waterSupplier() {
        return new WaterSupplier();
    }

    @Value("${coffee.type.1.temperature}")
    private String temperature1;
    @Value("${coffee.type.1.pressure}")
    private String pressure1;
    @Value("${coffee.type.1.countMilk}")
    private String countMilk1;
    @Value("${coffee.type.1.countWater}")
    private String countWater1;

    @Value("${coffee.type.2.temperature}")
    private String temperature2;
    @Value("${coffee.type.2.pressure}")
    private String pressure2;
    @Value("${coffee.type.2.countMilk}")
    private String countMilk2;
    @Value("${coffee.type.2.countWater}")
    private String countWater2;

    @Value("${coffee.type.3.temperature}")
    private String temperature3;
    @Value("${coffee.type.3.pressure}")
    private String pressure3;
    @Value("${coffee.type.3.countMilk}")
    private String countMilk3;
    @Value("${coffee.type.3.countWater}")
    private String countWater3;

    @Bean
    @Scope("prototype")
    public Set<CoffeeTypeConfiguration> coffeeConfigurations() {
        return new HashSet<CoffeeTypeConfiguration>(Arrays.asList(
                new CoffeeTypeConfiguration(Integer.parseInt(temperature1), Integer.parseInt(pressure1),
                        Integer.parseInt(countWater1), Integer.parseInt(countMilk1)),
                new CoffeeTypeConfiguration(Integer.parseInt(temperature2), Integer.parseInt(pressure2),
                        Integer.parseInt(countWater2), Integer.parseInt(countMilk2)),
                new CoffeeTypeConfiguration(Integer.parseInt(temperature3), Integer.parseInt(pressure3),
                        Integer.parseInt(countWater3), Integer.parseInt(countMilk3))));
    }

    @Bean
    @Scope("prototype")
    public CoffeeMachine coffeeMachine() {
        return new CoffeeMachine(capsuleModule(), heater(), milkSupplier(), waterSupplier(),
                coffeeConfigurations(), coffeeBrewer());
    }
}