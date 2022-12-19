package com.example.weatherSensorREST.services;

import com.example.weatherSensorREST.dao.StatDAO;
import com.example.weatherSensorREST.entities.Sensor;
import com.example.weatherSensorREST.entities.Stat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StatServiceTest {

  private final StatService statService;

  @Autowired
  StatServiceTest(StatService statService) {
    this.statService = statService;
  }

  @MockBean
  private SensorsService sensorsService;

  @MockBean
  private StatDAO statDAO;

  @Test
  void showStatistics() {
    List<Sensor> sensors = new ArrayList<>(Arrays.asList(new Sensor("Test0"),
        new Sensor("Test1"),
        new Sensor("Test2")));
    Mockito.when(sensorsService.showAll()).thenReturn(sensors);
    Mockito.when(statDAO.getStatisticsForSensor(Mockito.anyInt())).thenReturn(new Stat());
    statService.showStatistics();

    Mockito.verify(sensorsService, Mockito.times(1))
        .showAll();
    Mockito.verify(statDAO, Mockito.times(3))
        .getStatisticsForSensor(Mockito.anyInt());
  }
}