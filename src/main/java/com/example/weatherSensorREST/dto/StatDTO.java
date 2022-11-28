package com.example.weatherSensorREST.dto;

public class StatDTO {

  private double minValue;

  private double maxValue;

  private double avgValue;

  private double measurementsCount;

  private double rainyDaysCount;

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

  public void setMeasurementsCount(double measurementsCount) {
    this.measurementsCount = measurementsCount;
  }

  public double getRainyDaysCount() {
    return rainyDaysCount;
  }

  public void setRainyDaysCount(double rainyDaysCount) {
    this.rainyDaysCount = rainyDaysCount;
  }

  public String getSensorName() {
    return sensorName;
  }

  public void setSensorName(String sensorName) {
    this.sensorName = sensorName;
  }
}
