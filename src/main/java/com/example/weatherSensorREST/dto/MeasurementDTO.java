package com.example.weatherSensorREST.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

  @Min(value = -100, message = "temperature value can't be less than -100")
  @Max(value = 100, message = "temperature value can't be greater than 100")
  private double value;

  @NotNull(message = "This field can't be empty!")
  private boolean raining;

  @NotNull(message = "This field can't be empty!")
  private SensorDTO sensor;

  public MeasurementDTO(double value, boolean raining, SensorDTO sensor) {
    this.value = value;
    this.raining = raining;
    this.sensor = sensor;
  }

  public MeasurementDTO() {
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public boolean isRaining() {
    return raining;
  }

  public void setRaining(boolean raining) {
    this.raining = raining;
  }

  public SensorDTO getSensor() {
    return sensor;
  }

  public void setSensor(SensorDTO sensor) {
    this.sensor = sensor;
  }
}
