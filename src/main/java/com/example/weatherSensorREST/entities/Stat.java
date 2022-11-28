package com.example.weatherSensorREST.entities;


import com.example.weatherSensorREST.dto.SensorDTO;
import org.springframework.stereotype.Component;

@Component
public class Stat {

  private double minValue = 100;

  private double maxValue = -100;

  private double avgValue = 0;

  private int measurementsCount = 0;

  private int rainyDaysCount = 0;

  private double sumOfValues = 0;

  private String sensorName;

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

  public double getMeasurementsCount() {
    return measurementsCount;
  }

  public void addMeasurement() {
    measurementsCount++;
  }

  public double getRainyDaysCount() {
    return rainyDaysCount;
  }

  public void addRainyDay() {
    this.rainyDaysCount++;
  }

  public String getSensorName() {
    return sensorName;
  }

  public void setSensorName(String sensorName) {
    this.sensorName = sensorName;
  }

  public double getSumOfValues() {
    return sumOfValues;
  }

  public void plusValue(double value) {
    this.sumOfValues+=value;
  }
}
