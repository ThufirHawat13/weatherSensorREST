package com.example.weatherSensorREST.services;

import com.example.weatherSensorREST.dto.MeasurementDTO;
import com.example.weatherSensorREST.dto.StatDTO;
import com.example.weatherSensorREST.entities.Measurement;
import com.example.weatherSensorREST.entities.Sensor;
import com.example.weatherSensorREST.mapppers.MeasurementMapper;
import com.example.weatherSensorREST.repositories.MeasurementsRepository;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

  private final MeasurementsRepository measurementsRepository;

  private final MeasurementMapper measurementMapper;

  @Autowired
  public MeasurementsService(MeasurementsRepository measurementsRepository,
      MeasurementMapper measurementMapper) {
    this.measurementsRepository = measurementsRepository;
    this.measurementMapper = measurementMapper;
  }

  public List<MeasurementDTO> showAll() {
    return measurementMapper
        .convertToMeasurementDTOS(measurementsRepository.findAll());
  }

  @Transactional
  public void save(MeasurementDTO measurementDTO) {
    measurementsRepository
        .save(measurementMapper.convertToMeasurement(measurementDTO));
  }


}


