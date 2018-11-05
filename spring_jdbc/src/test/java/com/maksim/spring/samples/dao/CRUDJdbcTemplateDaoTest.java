package com.maksim.spring.samples.dao;

import com.maksim.spring.samples.model.Officer;
import com.maksim.spring.samples.model.Station;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CRUDJdbcTemplateDaoTest {
    private CRUDJdbcTemplateDao dao;

    @Before
    public void configureContext() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:spring/spring-context.xml");
        dao = context.getBean("CRUDJdbcTemplateDao", CRUDJdbcTemplateDao.class);
        context.close();
    }

    @Test
    public void onlyStationCRUDOperationsTest() {
        // create station
        Station station = new Station();
        station.setName("newStation");
        station.setLine("1 - red");
        dao.insertStation(station);
        //check create station
        Assert.assertTrue(station.getId() > 0);
        // read stations
        int station_id = station.getId();
        List<Station> allStations = dao.getAllStation();
        // check read stations
        Assert.assertFalse(allStations.isEmpty());
        Assert.assertTrue(allStations.contains(station));
        // update station
        String newSubwayLine = "2 - green";
        station.setLine(newSubwayLine);
        dao.updateStation(station);
        // get station by id
        Station updateStation = dao.getStationById(station_id);
        //check read station by id
        Assert.assertNotNull(updateStation);
        // check update station
        Assert.assertEquals(newSubwayLine, updateStation.getLine());
        // delete station
        dao.deleteStation(station);
        // check delete
        Station deletedStation = dao.getStationById(station_id);
        Assert.assertNull(deletedStation);
    }

    @Test
    public void stationWithOfficerCreateReadOperationTest() {
        // create station with officers
        Officer officer1 = new Officer();
        officer1.setName("Petr");
        officer1.setPosition("Guard at the entrance");

        Officer officer2 = new Officer();
        officer2.setName("Elena");
        officer2.setPosition("Escalator controller");

        Station station = new Station();
        station.setName("Tehnologisheskiy instityt");
        station.setLine("1 - blue");
        station.setOfficers(new ArrayList<>(Arrays.asList(officer1, officer2)));

        dao.insertStationWithOfficer(station);
        // read stations with officers
        List<Station> allStationWithOfficers = dao.getAllStationWithOfficers();
        //check insert and read stations
        Assert.assertTrue(station.getId() > 0);
        Assert.assertFalse(allStationWithOfficers.isEmpty());
        Assert.assertTrue(allStationWithOfficers.contains(station));
    }
}
