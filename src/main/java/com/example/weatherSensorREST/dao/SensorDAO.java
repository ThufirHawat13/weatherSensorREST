package com.example.weatherSensorREST.dao;

import com.example.weatherSensorREST.entities.Measurement;
import com.example.weatherSensorREST.entities.Sensor;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class SensorDAO {

  private final EntityManager entityManager;

  @Autowired
  public SensorDAO(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<Measurement> showAllSensorMeasurements(int id) {
    Session session = entityManager.unwrap(Session.class);
    Sensor sensor = session.get(Sensor.class, id);
    System.out.println(sensor.getMeasurements());
    return sensor.getMeasurements();
  }

}
