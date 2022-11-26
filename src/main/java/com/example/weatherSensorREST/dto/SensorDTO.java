package com.example.weatherSensorREST.dto;

import javax.validation.constraints.Size;

public class SensorDTO {

  @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters!")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
