package com.example.weatherSensorREST.entities;


import com.example.weatherSensorREST.dto.SensorDTO;
import org.springframework.stereotype.Component;

@Component
public class Stat {

  private double minValue = 0;

  private double maxValue = 0;

  private double avgValue = 0;

  private long daysCount = 0;

  private long rainyDaysCount = 0;

  private Sensor sensor;

  public double getMinValue() {
    return minValue;
  }

  public void setMinValue(double minValue) {
    this.minValue = minValue;
  }

  public double getMaxValue() {
    return maxValue;
  }

  public void setMaxValue(double maxValue) {
    this.maxValue = maxValue;
  }

  public double getAvgValue() {
    return avgValue;
  }

  public void setAvgValue(double avgValue) {
    this.avgValue = avgValue;
  }

  public long getDaysCount() {
    return daysCount;
  }

  public void setDaysCount(long daysCount) {
    this.daysCount = daysCount;
  }

  public long getRainyDaysCount() {
    return rainyDaysCount;
  }

  public void setRainyDaysCount(long rainyDaysCount) {
    this.rainyDaysCount = rainyDaysCount;
  }

  public Sensor getSensor() {
    return sensor;
  }

  public void setSensor(Sensor sensor) {
    this.sensor = sensor;
  }
}
