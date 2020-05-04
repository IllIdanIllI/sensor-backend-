package com.sensor.dao;

import com.sensor.model.Sensor;
import com.sensor.model.transport.PaginateEntity;

import java.util.List;

public interface SensorDao {

    Sensor findById(long id);

    Long save(Sensor entity);

    void update(Sensor entity);

    void delete(Sensor entity);

    List<Sensor> findEntitiesPagination(int currentPage, int limit);

    Long findEntitiesAmountPagination();

    PaginateEntity<Sensor> findBySearchCriteria(String searchCriteria, int currentPage, int limit);
}
