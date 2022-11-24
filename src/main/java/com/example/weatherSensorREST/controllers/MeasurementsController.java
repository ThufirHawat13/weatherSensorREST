package com.example.weatherSensorREST.controllers;

import com.example.weatherSensorREST.DTO.MeasurementDTO;
import com.example.weatherSensorREST.services.MeasurementsService;
import com.example.weatherSensorREST.services.SensorsService;
import com.example.weatherSensorREST.util.MeasurementErrorResponse;
import com.example.weatherSensorREST.util.MeasurementException;
import com.example.weatherSensorREST.util.RainyDaysCount;
import com.example.weatherSensorREST.util.SensorNotFoundValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final SensorNotFoundValidator sensorNotFoundValidator;

    private final MeasurementsService measurementsService;

    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsController(SensorNotFoundValidator sensorNotFoundValidator, MeasurementsService measurementsService, SensorsService sensorsService) {
        this.sensorNotFoundValidator = sensorNotFoundValidator;
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        sensorNotFoundValidator.validate(measurementDTO.getSensor(), bindingResult);
        if(bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new MeasurementException(errorMessage.toString());
        }
        measurementsService.save(measurementDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public List<MeasurementDTO> showAllMeasurements() {
        return measurementsService.showAll().stream()
                        .map(measurement -> measurementsService.convertToMeasurementDTO(measurement))
                        .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public RainyDaysCount ShowRainyDaysCount() {
        return measurementsService.rainyDaysCount();
    }







}
