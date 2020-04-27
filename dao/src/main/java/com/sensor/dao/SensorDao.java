package com.sensor.dao;

import com.sensor.model.Sensor;

import java.util.List;

public interface SensorDao {

    Sensor findById(long id);

    void save(Sensor entity);

    void delete(Sensor entity);

    List<Sensor> findEntitiesPagination(int currentPage, int limit);

    Long findEntitiesAmountPagination();

    List<Sensor> findBySearchCriteria(String searchCriteria, int currentPage, int limit);
}
