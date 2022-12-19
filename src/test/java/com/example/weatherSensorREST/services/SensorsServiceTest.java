package com.example.weatherSensorREST.services;

import static org.junit.jupiter.api.Assertions.*;

import com.example.weatherSensorREST.dto.SensorDTO;
import com.example.weatherSensorREST.entities.Sensor;
import com.example.weatherSensorREST.mapppers.SensorMapper;
import com.example.weatherSensorREST.repositories.SensorsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SensorsServiceTest {

  private final SensorsService sensorsService;

  @Autowired
  public SensorsServiceTest(SensorsService sensorsService) {
    this.sensorsService = sensorsService;
  }

  @MockBean
  private SensorsRepository sensorsRepository;

  @MockBean
  private SensorMapper sensorMapper;

  @Test
  void showAll() {
    sensorsService.showAll();

    Mockito.verify(sensorsRepository, Mockito.times(1))
        .findAll();
  }

  @Test
  void findByName() {
    String name = "testName";
    sensorsService.findByName(name);

    Mockito.verify(sensorsRepository, Mockito.times(1))
        .findByName(name);
  }

  @Test
  void save() {
    SensorDTO sensorDTO = Mockito.mock(SensorDTO.class);
    Sensor sensor = Mockito.mock(Sensor.class);
    Mockito.when(sensorMapper.convertToSensor(sensorDTO))
        .thenReturn(sensor);
    sensorsService.save(sensorDTO);

    Mockito.verify(sensorMapper, Mockito.times(1))
        .convertToSensor(sensorDTO);
    Mockito.verify(sensorsRepository, Mockito.times(1))
        .save(sensor);
  }
}