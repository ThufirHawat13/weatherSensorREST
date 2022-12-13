package com.example.weatherSensorREST.dao;

import com.example.weatherSensorREST.entities.Measurement;
import com.example.weatherSensorREST.entities.Sensor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SensorDAOTest {

  private final SensorDAO sensorDAO;

  @Autowired
  SensorDAOTest(SensorDAO sensorDAO) {
    this.sensorDAO = sensorDAO;
  }

  @MockBean
  private EntityManager entityManager;

  @Mock
  private Session session;


  @Test
  void showAllSensorMeasurements() {
    List<Measurement> measurements = new ArrayList<>(Arrays.asList(
        new Measurement(0.0, true),
        new Measurement(0.0, true),
        new Measurement(0.0, true)
    ));
    Sensor sensor = new Sensor("Test");
    sensor.setMeasurements(measurements);
    Mockito.when(entityManager.unwrap(Session.class)).thenReturn(session);
    Mockito.when(session.get(Sensor.class, 1))
        .thenReturn(sensor);

    Assertions.assertEquals(measurements,
        sensorDAO.showAllSensorMeasurements(1));
    Mockito.verify(session, Mockito.times(1)).get(Sensor.class, 1);
  }
}