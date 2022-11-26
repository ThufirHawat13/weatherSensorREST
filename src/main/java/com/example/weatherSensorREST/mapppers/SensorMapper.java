package com.example.weatherSensorREST.mapppers;

import com.example.weatherSensorREST.dto.SensorDTO;
import com.example.weatherSensorREST.entities.Sensor;
import org.springframework.stereotype.Component;

@Component
public class SensorMapper {

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
