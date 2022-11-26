package com.example.weatherSensorREST.util;

import com.example.weatherSensorREST.dto.SensorDTO;
import com.example.weatherSensorREST.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorDuplicateValidator implements Validator {

  private final SensorsService sensorsService;

  @Autowired
  public SensorDuplicateValidator(SensorsService sensorsService) {
    this.sensorsService = sensorsService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return SensorDTO.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    SensorDTO sensor = (SensorDTO) target;
    if (sensorsService.findByName(sensor.getName()) != null) {
      errors.rejectValue("name", "",
          "This sensor is already exists in database!");
    }

  }
}
