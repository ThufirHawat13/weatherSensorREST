package com.example.weatherSensorREST.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Min(value = -100, message = "temperature value can't be less than -100")
  @Max(value = 100, message = "temperature value can't be greater than 100")
  @Column(name = "value")
  private double value;

  @NotNull(message = "This field must not be empty!")
  @Column(name = "raining")
  private boolean raining;

  @Column(name = "measurement_time")
  private LocalDateTime measurementTime;

  @NotNull(message = "This field can't be empty!")
  @ManyToOne
  @JoinColumn(name = "sensor_id",
      referencedColumnName = "id")
  private Sensor sensor;

  public Measurement(double value, boolean raining) {
    this.value = value;
    this.raining = raining;
  }

  public Measurement() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public Sensor getSensor() {
    return sensor;
  }

  public void setSensor(Sensor sensor) {
    this.sensor = sensor;
  }

  public LocalDateTime getMeasurementTime() {
    return measurementTime;
  }

  public void setMeasurementTime(LocalDateTime measurementTime) {
    this.measurementTime = measurementTime;
  }
}
