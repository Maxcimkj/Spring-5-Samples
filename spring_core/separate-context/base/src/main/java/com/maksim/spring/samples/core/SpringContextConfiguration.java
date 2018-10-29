package com.maksim.spring.samples.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan
@ImportResource(locations = {"classpath:spring/spring-child-context.xml"})
public class SpringContextConfiguration {
}
