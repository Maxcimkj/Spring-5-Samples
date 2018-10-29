package com.maksim.spring.samples.core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test for simple App.
 */
public class SpringContextTest {
    @Test
    public void createContext() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringContextConfiguration.class);

        BaseBean baseBean = context.getBean("baseBean", BaseBean.class);

        Assert.assertNotNull(baseBean);
        Assert.assertNotNull(baseBean.getChildBean());
    }
}
