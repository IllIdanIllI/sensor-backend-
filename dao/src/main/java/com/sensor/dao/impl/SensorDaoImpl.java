package com.sensor.dao.impl;

import com.sensor.dao.SensorDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SensorDaoImpl implements SensorDao {

    @Autowired
    private SessionFactory sessionFactory;
}
