package com.example.weatherSensorREST.controllers;

import com.example.weatherSensorREST.dto.SensorDTO;
import com.example.weatherSensorREST.mapppers.SensorMapper;
import com.example.weatherSensorREST.services.SensorsService;
import com.example.weatherSensorREST.util.SensorDuplicateException;
import com.example.weatherSensorREST.util.SensorErrorResponse;
import com.example.weatherSensorREST.util.SensorDuplicateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

  private final SensorsService sensorsService;
  private final SensorDuplicateValidator sensorDuplicateValidator;

  private final SensorMapper sensorMapper;

  @Autowired
  public SensorsController(SensorsService sensorsService,
      SensorDuplicateValidator sensorDuplicateValidator, SensorMapper sensorMapper) {
    this.sensorsService = sensorsService;
    this.sensorDuplicateValidator = sensorDuplicateValidator;
    this.sensorMapper = sensorMapper;
  }

  @PostMapping("/register")
  public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO,
      BindingResult bindingResult) {
    sensorDuplicateValidator.validate(sensorDTO, bindingResult);
    if (bindingResult.hasErrors()) {
      StringBuilder errorMessage = new StringBuilder();

      List<FieldError> errors = bindingResult.getFieldErrors();
      for (FieldError error : errors) {
        errorMessage.append(error.getField())
            .append(" - ").append(error.getDefaultMessage())
            .append(";");
      }
      throw new SensorDuplicateException(errorMessage.toString());
    }

    sensorsService.save(sensorMapper.convertToSensor(sensorDTO));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @ExceptionHandler
  private ResponseEntity<SensorErrorResponse> handleException(SensorDuplicateException e) {
    SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
        e.getMessage(),
        System.currentTimeMillis());
    return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
  }


}
