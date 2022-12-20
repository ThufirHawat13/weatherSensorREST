package com.example.weatherSensorREST.util;

import com.example.weatherSensorREST.dto.SensorDTO;
import com.example.weatherSensorREST.entities.Sensor;
import com.example.weatherSensorREST.services.SensorsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.Errors;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SensorDuplicateValidatorTest {

  private final SensorDuplicateValidator sensorDuplicateValidator;

  @Autowired
  public SensorDuplicateValidatorTest(SensorDuplicateValidator sensorDuplicateValidator) {
    this.sensorDuplicateValidator = sensorDuplicateValidator;
  }

  @MockBean
  private SensorsService sensorsService;

  @Mock
  private Errors errors;

  private static final String sensorName = "TestName";

  private static final SensorDTO sensorDTO = Mockito.mock(SensorDTO.class);

  @BeforeAll
  public static void setup() {
    Mockito.when(sensorDTO.getName())
        .thenReturn(sensorName);
  }

  @Test
  void validateSuccess() {
    Mockito.when(sensorsService.findByName(sensorDTO.getName()))
        .thenReturn(null);
    sensorDuplicateValidator.validate(sensorDTO, errors);

    Mockito.verify(errors, Mockito.times(0))
        .rejectValue(Mockito.anyString(),
            Mockito.anyString(),
            Mockito.anyString());
  }

  @Test
  void validateFail() {
    Sensor sensor = Mockito.mock(Sensor.class);
    Mockito.when(sensorsService.findByName(sensorDTO.getName()))
        .thenReturn(sensor);
    sensorDuplicateValidator.validate(sensorDTO, errors);

    Mockito.verify(errors, Mockito.times(1))
        .rejectValue(Mockito.anyString(),
            Mockito.anyString(),
            Mockito.anyString());
  }
}