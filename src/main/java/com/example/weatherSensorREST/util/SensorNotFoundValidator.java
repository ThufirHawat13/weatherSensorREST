package com.example.weatherSensorREST.util;

import com.example.weatherSensorREST.DTO.SensorDTO;
import com.example.weatherSensorREST.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorNotFoundValidator implements Validator {

  private final SensorsService sensorsService;

  @Autowired
  public SensorNotFoundValidator(SensorsService sensorsService) {
    this.sensorsService = sensorsService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return SensorDTO.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    SensorDTO sensor = (SensorDTO) target;
    if (sensorsService.findByName(sensor.getName()) == null) {
      errors.rejectValue("sensor", "",
          "Sensor with that name doesn't exists!");
    }
  }
}
