package com.example.weatherSensorREST.entities;


import com.example.weatherSensorREST.dto.SensorDTO;
import org.springframework.stereotype.Component;

@Component
public class Stat {

  private double minValue = 100;

  private double maxValue = -100;

  private double avgValue = 0;

  private long measurementsCount = 0;

  private long rainyDaysCount = 0;

  private double sumOfValues = 0;

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

  public Sensor getSensor() {
    return sensor;
  }

  public void setSensor(Sensor sensor) {
    this.sensor = sensor;
  }

  public double getSumOfValues() {
    return sumOfValues;
  }

  public void plusValue(double value) {
    this.sumOfValues+=value;
  }

  public long getMeasurementsCount() {
    return measurementsCount;
  }

  public void setMeasurementsCount(long measurementsCount) {
    this.measurementsCount = measurementsCount;
  }

  public long getRainyDaysCount() {
    return rainyDaysCount;
  }

  public void setRainyDaysCount(long rainyDaysCount) {
    this.rainyDaysCount = rainyDaysCount;
  }
}
