package com.example.weatherSensorREST.services;

import static org.junit.jupiter.api.Assertions.*;

import com.example.weatherSensorREST.dto.MeasurementDTO;
import com.example.weatherSensorREST.dto.SensorDTO;
import com.example.weatherSensorREST.entities.Measurement;
import com.example.weatherSensorREST.entities.Sensor;
import com.example.weatherSensorREST.mapppers.MeasurementMapper;
import com.example.weatherSensorREST.repositories.MeasurementsRepository;
import com.example.weatherSensorREST.repositories.SensorsRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
class MeasurementsServiceTest {

  private final MeasurementsService measurementsService;

  @Autowired
  public MeasurementsServiceTest(MeasurementsService measurementsService) {
    this.measurementsService = measurementsService;
  }

  @MockBean
  private MeasurementsRepository measurementsRepository;

  @MockBean
  private SensorsRepository sensorsRepository;

  @MockBean
  private MeasurementMapper measurementMapper;

  @Test
  void showAll() {
    measurementsService.showAll();

    Mockito.verify(measurementMapper, Mockito.times(1))
        .convertToMeasurementDTOS(Mockito.any());
    Mockito.verify(measurementsRepository, Mockito.times(1))
        .findAll();
  }

  @Test
  void save() {
    MeasurementDTO measurementDTO = Mockito.mock(MeasurementDTO.class);
    Measurement measurement = Mockito.mock(Measurement.class);
    Mockito.when(measurementMapper.convertToMeasurement(measurementDTO))
        .thenReturn(measurement);
    measurementsService.save(measurementDTO);

    Mockito.verify(measurementMapper, Mockito.times(1))
        .convertToMeasurement(measurementDTO);
    Mockito.verify(measurementsRepository, Mockito.times(1))
        .save(measurement);
  }
}