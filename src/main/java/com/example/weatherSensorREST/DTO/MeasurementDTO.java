package com.example.weatherSensorREST.DTO;

import com.example.weatherSensorREST.entities.Sensor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class MeasurementDTO {

    @Min(value = -100, message = "temperature value can't be less than -100")
    @Max(value = 100, message = "temperature value can't be greater than 100")
    private double value;

    private boolean raining;

    private SensorDTO sensor;

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
