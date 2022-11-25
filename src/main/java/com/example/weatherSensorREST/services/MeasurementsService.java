package com.example.weatherSensorREST.services;

import com.example.weatherSensorREST.DTO.MeasurementDTO;
import com.example.weatherSensorREST.entities.Measurement;
import com.example.weatherSensorREST.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {


    private final  SensorsService sensorsService;
    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(SensorsService sensorsService, MeasurementsRepository measurementsRepository) {
        this.sensorsService = sensorsService;
        this.measurementsRepository = measurementsRepository;
    }

    public List<Measurement> showAll() {
        return measurementsRepository.findAll();
    }

    @Transactional
    public void save(MeasurementDTO measurementDTO){
        measurementsRepository.save(convertToMeasurement(measurementDTO));
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
        measurementDTO.setSensor(sensorsService.convertToSensorDTO(measurement.getSensor()));
        return measurementDTO;
    }

    public Measurement enrich(Measurement measurement) {
        measurement.setMeasurementTime(LocalDateTime.now());
        return measurement;
    }



}
