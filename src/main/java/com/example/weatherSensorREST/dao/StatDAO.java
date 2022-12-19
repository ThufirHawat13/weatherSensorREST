package com.example.weatherSensorREST.dao;

import com.example.weatherSensorREST.entities.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StatDAO {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public StatDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Stat getStatisticsForSensor(int id) {
    return jdbcTemplate.query(
        "SELECT MIN(measurement.value) AS minValue,"
            + " MAX(measurement.value) AS maxValue,"
            + " AVG(measurement.value) AS avgValue,"
            + " COUNT(*) AS daysCount,"
            + " COUNT(*) FILTER(WHERE raining)"
            + " AS rainyDaysCount FROM measurement"
            + " WHERE Sensor_id = ?",
             new Object[]{id},
             new BeanPropertyRowMapper<>(Stat.class))
         .stream().findAny().orElse(new Stat());
  }

}
