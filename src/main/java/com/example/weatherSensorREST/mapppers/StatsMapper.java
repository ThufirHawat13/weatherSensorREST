package com.example.weatherSensorREST.mapppers;

import com.example.weatherSensorREST.dto.StatDTO;
import com.example.weatherSensorREST.entities.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatsMapper {

  private final SensorMapper sensorMapper;

  @Autowired
  public StatsMapper(SensorMapper sensorMapper) {
    this.sensorMapper = sensorMapper;
  }


  public StatDTO convertToStatDTO(Stat stat) {
    StatDTO statDTO = new StatDTO();
    statDTO.setMinValue(stat.getMinValue());
    statDTO.setMaxValue(stat.getMaxValue());
    statDTO.setAvgValue(Math.round(stat.getAvgValue() * 10d)/10d);
    statDTO.setRainyDaysCount(stat.getRainyDaysCount());
    statDTO.setMeasurementsCount(stat.getDaysCount());
    statDTO.setSensor(sensorMapper.convertToSensorDTO(stat.getSensor()));

    return statDTO;
  }

}
