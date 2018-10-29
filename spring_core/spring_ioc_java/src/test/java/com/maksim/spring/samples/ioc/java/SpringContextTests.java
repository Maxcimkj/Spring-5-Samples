package com.maksim.spring.samples.ioc.java;


import com.maksim.spring.samples.ioc.java.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * Unit test for xml spring context configuration
 */

public class SpringContextTests {
    AnnotationConfigApplicationContext ctx;

    @Before
    public void springContextConfigure() {
        ctx = new AnnotationConfigApplicationContext(SpringContextConfiguration.class);
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
        Set<CoffeeTypeConfiguration> coffeeConfigurations = ctx.getBean("coffeeConfigurations", Set.class);

        Assert.assertNotNull(coffeeConfigurations);
        Assert.assertEquals(3, coffeeConfigurations.size());
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

        Set<CoffeeTypeConfiguration> coffeeConfigurations = ctx.getBean("coffeeConfigurations", Set.class);
        Assert.assertEquals(coffeeConfigurations, coffeeMachine.getCoffeeTypeConfigurations());
    }

    @After
    public void springContextClose() {
        ctx.close();
    }
}
