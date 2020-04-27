package com.sensor.mapper.user;

import com.sensor.dto.details.ApplicationUserDetails;
import com.sensor.mapper.EntityMapper;
import com.sensor.model.User;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUserDetailsMapper extends EntityMapper<ApplicationUserDetails, User> {
    @Override
    public ApplicationUserDetails map(User objectToMap) {
        return ApplicationUserDetails.builder()
                .login(objectToMap.getLogin())
                .password(objectToMap.getPassword())
                .role(objectToMap.getRole().getRole())
                .build();
    }
}
