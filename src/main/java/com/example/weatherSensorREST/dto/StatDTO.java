package com.example.weatherSensorREST.dto;

public class StatDTO {

  private double minValue;

  private double maxValue;

  private double avgValue;

  private long measurementsCount;

  private long rainyDaysCount;

  private SensorDTO sensor;

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

  public SensorDTO getSensor() {
    return sensor;
  }

  public void setSensor(SensorDTO sensor) {
    this.sensor = sensor;
  }
}
