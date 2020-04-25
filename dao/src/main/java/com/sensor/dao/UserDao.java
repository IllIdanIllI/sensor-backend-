package com.sensor.dao;

import com.sensor.model.User;

public interface UserDao {
    User findById(long id);

    User save(User entity);

    void delete(User entity);
}
