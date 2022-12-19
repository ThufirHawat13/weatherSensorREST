package com.example.weatherSensorREST.services;


import com.example.weatherSensorREST.dto.SensorDTO;
import com.example.weatherSensorREST.entities.Sensor;
import com.example.weatherSensorREST.mapppers.SensorMapper;
import com.example.weatherSensorREST.repositories.SensorsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

  private final SensorsRepository sensorsRepository;

  private final SensorMapper sensorMapper;

  @Autowired
  public SensorsService(SensorsRepository sensorsRepository, SensorMapper sensorMapper) {
    this.sensorsRepository = sensorsRepository;
    this.sensorMapper = sensorMapper;
  }

  public List<Sensor> showAll() {
    return sensorsRepository.findAll();
  }

  public Sensor findByName(String name) {
    Optional<Sensor> sensor = sensorsRepository.findByName(name);
    return sensor.orElse(null);
  }

  @Transactional
  public void save(SensorDTO sensorDTO) {
    sensorsRepository.save(sensorMapper.convertToSensor(sensorDTO));
  }


}

