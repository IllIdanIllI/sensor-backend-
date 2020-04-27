package com.sensor.service;

import com.sensor.dto.details.ApplicationUserDetails;
import com.sensor.dto.request.UserRequestDto;
import com.sensor.model.User;

public interface UserService {
    ApplicationUserDetails findByLogin(String login);

    boolean checkUserCredentials(ApplicationUserDetails dto, String encodedPassword);

    User findById(Long id);

    Long save(UserRequestDto dto);
}

