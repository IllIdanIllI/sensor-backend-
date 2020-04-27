package com.sensor.dao;

import com.sensor.model.User;

public interface UserDao {
    User findById(long id);

    void save(User entity);

    void delete(User entity);

    User findByLogin(String login);
}
