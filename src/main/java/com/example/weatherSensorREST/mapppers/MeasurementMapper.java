package com.example.weatherSensorREST.mapppers;

import com.example.weatherSensorREST.dto.MeasurementDTO;
import com.example.weatherSensorREST.entities.Measurement;
import com.example.weatherSensorREST.services.SensorsService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasurementMapper {

  private final SensorsService sensorsService;

  private final SensorMapper sensorMapper;

  @Autowired
  public MeasurementMapper(SensorsService sensorsService, SensorMapper sensorMapper) {
    this.sensorsService = sensorsService;
    this.sensorMapper = sensorMapper;
  }

  public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
    Measurement measurement = new Measurement();
    measurement.setValue(measurementDTO.getValue());
    measurement.setRaining(measurementDTO.isRaining());
    measurement.setSensor(sensorsService.findByName(measurementDTO.getSensor().getName()));
    return enrich(measurement);
  }

  public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
    MeasurementDTO measurementDTO = new MeasurementDTO();
    measurementDTO.setValue(measurement.getValue());
    measurementDTO.setRaining(measurement.isRaining());
    measurementDTO.setSensor(sensorMapper.convertToSensorDTO(measurement.getSensor()));
    return measurementDTO;
  }

  public List<MeasurementDTO> convertToMeasurementDTOS(List<Measurement> measurements) {
    return measurements.stream()
        .map(m -> convertToMeasurementDTO(m))
        .collect(Collectors.toList());
  }

  public Measurement enrich(Measurement measurement) {
    measurement.setMeasurementTime(LocalDateTime.now());
    return measurement;
  }

}
