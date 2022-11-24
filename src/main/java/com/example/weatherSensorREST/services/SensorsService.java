package com.example.weatherSensorREST.services;


import com.example.weatherSensorREST.DTO.SensorDTO;
import com.example.weatherSensorREST.entities.Sensor;
import com.example.weatherSensorREST.repositories.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }


    public Sensor findByName(String name) {
        Optional<Sensor> sensor = sensorsRepository.findByName(name);
        return sensor.orElse(null);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
        }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        sensor.setName(sensorDTO.getName());
        return sensor;
    }

    public SensorDTO convertToSensorDTO(Sensor sensor) {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setName(sensor.getName());
        return sensorDTO;
    }


}

