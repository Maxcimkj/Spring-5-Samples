package com.maksim.spring.samples.ioc.xml;


import com.maksim.spring.samples.ioc.xml.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Unit test for xml spring context configuration
 */

public class SpringContextTests {
    GenericXmlApplicationContext ctx;

    @Before
    public void springContextConfigure() {
        ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/spring-context.xml");
        ctx.refresh();
    }

    @Test
    public void capsuleModuleBeanConfigureTest() {
        Object capsuleModule1 = ctx.getBean("capsuleModule", CapsuleModule.class);
        Object capsuleModule2 = ctx.getBean("capsuleModule", CapsuleModule.class);

        Assert.assertNotNull(capsuleModule1);
        Assert.assertNotNull(capsuleModule2);
        Assert.assertNotEquals(capsuleModule1, capsuleModule2);
    }


    @Test
    public void waterSupplierBeanConfigureTest() {
        WaterSupplier waterSupplier1 = ctx.getBean("waterSupplier", WaterSupplier.class);
        WaterSupplier waterSupplier2 = ctx.getBean("waterSupplier", WaterSupplier.class);

        Assert.assertNotNull(waterSupplier1);
        Assert.assertNotNull(waterSupplier2);
        Assert.assertNotEquals(waterSupplier1, waterSupplier2);
    }

    @Test
    public void milkSupplierBeanConfigureTest() {
        MilkSupplier milkSupplier1 = ctx.getBean("milkSupplier", MilkSupplier.class);
        MilkSupplier milkSupplier2 = ctx.getBean("milkSupplier", MilkSupplier.class);

        Assert.assertNotNull(milkSupplier1);
        Assert.assertNotNull(milkSupplier2);
        Assert.assertNotEquals(milkSupplier1, milkSupplier2);
    }

    @Test
    public void coffeeBrewerBeanConfigureTest() {
        CoffeeBrewer coffeeBrewer1 = ctx.getBean("coffeeBrewer", CoffeeBrewer.class);
        CoffeeBrewer coffeeBrewer2 = ctx.getBean("coffeeBrewer", CoffeeBrewer.class);

        Assert.assertNotNull(coffeeBrewer1);
        Assert.assertNotNull(coffeeBrewer2);
        Assert.assertNotEquals(coffeeBrewer1, coffeeBrewer2);
    }

    @Test
    public void heaterBeanConfigureTest() {
        Heater heater1 = ctx.getBean("heater", Heater.class);
        Heater heater2 = ctx.getBean("heater", Heater.class);

        Assert.assertNotNull(heater1);
        Assert.assertNotNull(heater2);
        Assert.assertNotEquals(heater1, heater2);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void coffeeTypeConfigureTest() {
        CoffeeTypeConfiguration coffee1 = ctx.getBean("coffee1", CoffeeTypeConfiguration.class);
        CoffeeTypeConfiguration coffee2 = ctx.getBean("coffee2", CoffeeTypeConfiguration.class);
        CoffeeTypeConfiguration coffee3 = ctx.getBean("coffee3", CoffeeTypeConfiguration.class);

        Set<CoffeeTypeConfiguration> coffeeConfigurations = ctx.getBean("coffeeConfiguration", Set.class);

        Assert.assertNotNull(coffeeConfigurations);
        Assert.assertTrue(coffeeConfigurations.contains(coffee1));
        Assert.assertTrue(coffeeConfigurations.contains(coffee2));
        Assert.assertTrue(coffeeConfigurations.contains(coffee3));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void coffeeMachineConfigureTest() {
        CoffeeMachine coffeeMachine = ctx.getBean("coffeeMachine", CoffeeMachine.class);

        Assert.assertNotNull(coffeeMachine);
        Assert.assertTrue(coffeeMachine.getCapsuleModule() != null
                && coffeeMachine.getCapsuleModule() instanceof CapsuleModule);
        Assert.assertTrue(coffeeMachine.getCoffeeBrewer() != null
                && coffeeMachine.getCoffeeBrewer() instanceof CoffeeBrewer);
        Assert.assertTrue(coffeeMachine.getMilkSupplier() != null
                && coffeeMachine.getMilkSupplier() instanceof MilkSupplier);
        Assert.assertTrue(coffeeMachine.getWaterSupplier() != null
                && coffeeMachine.getWaterSupplier() instanceof WaterSupplier);
        Assert.assertTrue(coffeeMachine.getHeater() != null
                && coffeeMachine.getHeater() instanceof Heater);

        Set<CoffeeTypeConfiguration> coffeeConfigurations = ctx.getBean("coffeeConfiguration", Set.class);
        Assert.assertEquals(coffeeConfigurations, coffeeMachine.getCoffeeTypeConfigurations());
    }

    @Test
    public void propertyValueConfigureTest() throws Exception {
        Properties prop = new Properties();
        InputStream input = ctx.getResource("classpath:configurations.properties").getInputStream();
        prop.load(input);

        String coffeeType1TemperatureValue = prop.getProperty("coffee.type.1.temperature");
        String coffeeType1PressureValue = prop.getProperty("coffee.type.1.pressure");
        String coffeeType1CountMilkValue = prop.getProperty("coffee.type.1.countMilk");
        String coffeeType1CountWaterValue = prop.getProperty("coffee.type.1.countWater");

        CoffeeTypeConfiguration coffee1 = ctx.getBean("coffee1", CoffeeTypeConfiguration.class);
        Assert.assertEquals(coffeeType1TemperatureValue, Integer.toString(coffee1.getTemperature()));
        Assert.assertEquals(coffeeType1PressureValue, Integer.toString(coffee1.getPressure()));
        Assert.assertEquals(coffeeType1CountMilkValue, Integer.toString(coffee1.getCountMilk()));
        Assert.assertEquals(coffeeType1CountWaterValue, Integer.toString(coffee1.getCountWater()));

        String coffeeType2TemperatureValue = prop.getProperty("coffee.type.2.temperature");
        String coffeeType2PressureValue = prop.getProperty("coffee.type.2.pressure");
        String coffeeType2CountMilkValue = prop.getProperty("coffee.type.2.countMilk");
        String coffeeType2CountWaterValue = prop.getProperty("coffee.type.2.countWater");

        CoffeeTypeConfiguration coffee2 = ctx.getBean("coffee2", CoffeeTypeConfiguration.class);
        Assert.assertEquals(coffeeType2TemperatureValue, Integer.toString(coffee2.getTemperature()));
        Assert.assertEquals(coffeeType2PressureValue, Integer.toString(coffee2.getPressure()));
        Assert.assertEquals(coffeeType2CountMilkValue, Integer.toString(coffee2.getCountMilk()));
        Assert.assertEquals(coffeeType2CountWaterValue, Integer.toString(coffee2.getCountWater()));

        String coffeeType3TemperatureValue = prop.getProperty("coffee.type.3.temperature");
        String coffeeType3PressureValue = prop.getProperty("coffee.type.3.pressure");
        String coffeeType3CountMilkValue = prop.getProperty("coffee.type.3.countMilk");
        String coffeeType3CountWaterValue = prop.getProperty("coffee.type.3.countWater");

        CoffeeTypeConfiguration coffee3 = ctx.getBean("coffee3", CoffeeTypeConfiguration.class);
        Assert.assertEquals(coffeeType3TemperatureValue, Integer.toString(coffee3.getTemperature()));
        Assert.assertEquals(coffeeType3PressureValue, Integer.toString(coffee3.getPressure()));
        Assert.assertEquals(coffeeType3CountMilkValue, Integer.toString(coffee3.getCountMilk()));
        Assert.assertEquals(coffeeType3CountWaterValue, Integer.toString(coffee3.getCountWater()));
    }


    @After
    public void springContextClose() {
        ctx.close();
    }
}
