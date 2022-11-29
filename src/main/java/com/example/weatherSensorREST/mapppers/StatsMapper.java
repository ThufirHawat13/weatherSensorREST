package com.example.weatherSensorREST.mapppers;

import com.example.weatherSensorREST.dto.StatDTO;
import com.example.weatherSensorREST.entities.Stat;
import org.springframework.stereotype.Component;

@Component
public class StatsMapper {

  public StatDTO convertToStatDTO(Stat stat) {
    StatDTO statDTO = new StatDTO();
    double avg = stat.getSumOfValues() / stat.getMeasurementsCount();
    statDTO.setMinValue(stat.getMinValue());
    statDTO.setMaxValue(stat.getMaxValue());
    statDTO.setAvgValue(Math.round(avg * 10d) / 10d);
    statDTO.setRainyDaysCount(stat.getRainyDaysCount());
    statDTO.setMeasurementsCount(stat.getMeasurementsCount());
    statDTO.setSensorName(stat.getSensorName());

    return statDTO;
  }

}
