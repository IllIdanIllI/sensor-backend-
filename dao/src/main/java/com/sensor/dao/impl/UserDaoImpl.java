package com.sensor.dao.impl;

import com.sensor.dao.GenericDao;
import com.sensor.dao.UserDao;
import com.sensor.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDao<User, Long> implements UserDao {

}
