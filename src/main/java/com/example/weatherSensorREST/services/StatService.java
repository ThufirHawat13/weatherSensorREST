package com.example.weatherSensorREST.services;

import com.example.weatherSensorREST.dao.SensorDAO;
import com.example.weatherSensorREST.entities.Measurement;
import com.example.weatherSensorREST.entities.Stat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StatService {

  private final SensorsService sensorsService;
  private final MeasurementsService measurementsService;

  private final SensorDAO sensorDAO;

  @Autowired
  public StatService(SensorsService sensorsService, MeasurementsService measurementsService,
      SensorDAO sensorDAO) {
    this.sensorsService = sensorsService;
    this.measurementsService = measurementsService;
    this.sensorDAO = sensorDAO;
  }


  public List<Stat> showStatistics() {
    List<Stat> stats = new ArrayList<>();
    sensorsService.showAll().stream().forEachOrdered(sensor -> {
      List<Measurement> measurements = sensorDAO.showAllSensorMeasurements(sensor.getId());
      Stat stat = new Stat();
      stat.setSensor(sensor);
      stat.setMinValue(
          measurements.stream().mapToDouble(Measurement::getValue).min().getAsDouble());
      stat.setMaxValue(
          measurements.stream().mapToDouble(Measurement::getValue).max().getAsDouble());
      stat.setAvgValue(
          measurements.stream().mapToDouble(Measurement::getValue).average().getAsDouble());
      stat.setRainyDaysCount(
          measurements.stream().filter(measurement -> measurement.isRaining()).count());
      stat.setMeasurementsCount(measurements.stream().count());
      stats.add(stat);
    });
    return stats;
  }
}