package com.example.weatherSensorREST.services;

import com.example.weatherSensorREST.dao.StatDAO;
import com.example.weatherSensorREST.entities.Stat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StatService {

  private final SensorsService sensorsService;
  private final StatDAO statDAO;

  @Autowired
  public StatService(SensorsService sensorsService, StatDAO statDAO) {
    this.sensorsService = sensorsService;
    this.statDAO = statDAO;
  }


  public List<Stat> showStatistics() {
    List<Stat> stats = new ArrayList<>();
    sensorsService.showAll().stream().forEachOrdered(sensor -> {
      Stat stat = statDAO.getStatisticsForSensor(sensor.getId());
      stat.setSensor(sensor);
      stats.add(stat);
    });
    return stats;
  }


}