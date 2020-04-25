package com.sensor.controller;

import com.sensor.dao.UserDao;
import com.sensor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserDao service;

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable Long id) {
        User user = service.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
