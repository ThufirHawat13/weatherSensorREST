package com.example.weatherSensorREST.services;

import com.example.weatherSensorREST.dto.StatDTO;
import com.example.weatherSensorREST.entities.Measurement;
import com.example.weatherSensorREST.entities.Sensor;
import com.example.weatherSensorREST.entities.Stat;
import com.example.weatherSensorREST.mapppers.MeasurementMapper;
import com.example.weatherSensorREST.mapppers.SensorMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StatService {

  private final SensorsService sensorsService;
  private final MeasurementsService measurementsService;

  @Autowired
  public StatService(SensorsService sensorsService, MeasurementsService measurementsService) {
    this.sensorsService = sensorsService;
    this.measurementsService = measurementsService;
  }


  public List<Stat> showStatistics() {
    List<Stat> stats = new ArrayList<>();

    for (Sensor sensor : sensorsService.showAll()) {
      Stat stat = new Stat();
      stat.setSensorName(sensor.getName());
      stats.add(stat);
    }

    for (Measurement measurement : measurementsService.showAll()) {
      double value = measurement.getValue();
      for (Stat stat : stats) {
        if (measurement.getSensor().getName().equals(stat.getSensorName())) {
          if (value > stat.getMaxValue()) {
            stat.setMaxValue(value);
          }
          if (value < stat.getMinValue()) {
            stat.setMinValue(value);
          }
          if (measurement.isRaining()) {
            stat.addRainyDay();
          }
          stat.addMeasurement();
          stat.plusValue(measurement.getValue());
        }
      }
    }
    return stats;
  }
}