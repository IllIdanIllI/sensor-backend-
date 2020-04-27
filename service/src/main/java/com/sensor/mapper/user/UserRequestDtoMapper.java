package com.sensor.mapper.user;

import com.sensor.dto.request.UserRequestDto;
import com.sensor.mapper.EntityMapper;
import com.sensor.model.User;
import com.sensor.model.type.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDtoMapper extends EntityMapper<User, UserRequestDto> {
    @Override
    public User map(UserRequestDto objectToMap) {
        User user = new User();
        user.setLogin(objectToMap.getLogin());
        user.setPassword(objectToMap.getPassword());
        user.setRole(UserRole.valueOf(objectToMap.getRole().toUpperCase()));
        return user;
    }
}
