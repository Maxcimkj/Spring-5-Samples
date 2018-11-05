package com.maksim.spring.samples.dao;

import com.maksim.spring.samples.model.Station;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class JdbcTemplateDaoTest {
    private JdbcTemplateOnlyDao dao;

    @Before
    public void configureContext() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:spring/spring-context.xml");
        dao = context.getBean("jdbcTemplateDao", JdbcTemplateOnlyDao.class);
        context.close();
    }

    @Test
    public void createReadDeleteTest() {
        // create
        Station station = new Station();
        station.setName("Dostoevskay");
        station.setLine("4 - orange");
        dao.insertStation(station);
        // read
        List<String> stationNamesAfterInsert = dao.getStationNames();
        // check read
        Assert.assertNotNull(stationNamesAfterInsert);
        Assert.assertFalse(stationNamesAfterInsert.isEmpty());
        // check create
        Assert.assertTrue(stationNamesAfterInsert.contains(station.getName()));
        // delete
        dao.deleteStationByName(station.getName());
        // check delete
        List<String> stationNamesAfterDelete = dao.getStationNames();
        Assert.assertFalse(stationNamesAfterDelete.contains(station.getName()));
    }
}
