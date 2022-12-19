package com.example.weatherSensorREST.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.example.weatherSensorREST.dto.SensorDTO;
import com.example.weatherSensorREST.entities.Sensor;
import com.example.weatherSensorREST.mapppers.SensorMapper;
import com.example.weatherSensorREST.services.SensorsService;
import com.example.weatherSensorREST.util.SensorDuplicateValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
@WebMvcTest(SensorsController.class)
class SensorsControllerTest {

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  @Autowired
  public SensorsControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
  }

  @MockBean
  private SensorsService sensorsService;

  @MockBean
  private SensorDuplicateValidator sensorDuplicateValidator;

  @MockBean
  private SensorMapper sensorMapper;

  @Mock
  private BindingResult bindingResult;

  @Test
  void registerSensor() throws Exception {
    SensorDTO sensor = new SensorDTO("Test");

    mockMvc.perform(
            MockMvcRequestBuilders.post("/sensors/register")
                .content(objectMapper.writeValueAsString(sensor))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(sensorDuplicateValidator, Mockito.times(1))
        .validate(Mockito.any(), Mockito.any());
    Mockito.verify(sensorMapper, Mockito.times(1))
        .convertToSensor(Mockito.any());
    Mockito.verify(sensorsService, Mockito.times(1))
        .save(Mockito.any());

  }

  @Test
  void registerSensorFail() throws Exception {
    SensorDTO sensor = new SensorDTO("T");

    mockMvc.perform(
            MockMvcRequestBuilders.post("/sensors/register")
                .content(objectMapper.writeValueAsString(sensor))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    Mockito.verify(sensorDuplicateValidator, Mockito.times(1))
        .validate(Mockito.any(), Mockito.any());
    Mockito.verify(sensorMapper, Mockito.times(0))
        .convertToSensorDTO(Mockito.any());
    Mockito.verify(sensorsService, Mockito.times(0))
        .save(Mockito.any());
  }
}