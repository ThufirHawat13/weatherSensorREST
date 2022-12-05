package com.example.weatherSensorREST.controllers;

import com.example.weatherSensorREST.dto.MeasurementDTO;
import com.example.weatherSensorREST.entities.Measurement;
import com.example.weatherSensorREST.entities.Sensor;
import com.example.weatherSensorREST.mapppers.MeasurementMapper;
import com.example.weatherSensorREST.repositories.MeasurementsRepository;
import com.example.weatherSensorREST.services.MeasurementsService;
import com.example.weatherSensorREST.util.SensorNotFoundValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MeasurementsController.class)
class MeasurementsControllerTest {

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;


  @Autowired
  MeasurementsControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
  }

  @MockBean
  private SensorNotFoundValidator sensorNotFoundValidator;

  @MockBean
  private MeasurementsService measurementsService;

  @MockBean
  private MeasurementsRepository measurementsRepository;

  @MockBean
  private MeasurementMapper measurementMapper;


  @MockBean
  private BindingResult bindingResult;

  @Test
  void addMeasurement() throws Exception {
    Measurement measurement = new Measurement(0.0, true);
    measurement.setSensor(new Sensor("Test"));

    mockMvc.perform(
            MockMvcRequestBuilders.post("/measurements/add")
                .content(objectMapper.writeValueAsString(measurement))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(sensorNotFoundValidator, Mockito.times(1))
        .validate(Mockito.any(), Mockito.any());
    Mockito.verify(measurementsService, Mockito.times(1))
        .save(Mockito.any());
  }

  @Test
  void addMeasurementFail() throws Exception {
    Measurement measurement = new Measurement(-101.0, true);
    measurement.setSensor(new Sensor(null));

    mockMvc.perform(
            MockMvcRequestBuilders.post("/measurements/add")
                .content(objectMapper.writeValueAsString(measurement))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    Mockito.verify(sensorNotFoundValidator, Mockito.times(1))
        .validate(Mockito.any(), Mockito.any());
    Mockito.verify(measurementsService, Mockito.times(0))
        .save(Mockito.any());
  }

  @Test
  void showAllMeasurements() {

  }

}