package com.sensor.service.impl;

import com.sensor.dao.UserDao;
import com.sensor.dto.details.ApplicationUserDetails;
import com.sensor.dto.request.UserRequestDto;
import com.sensor.mapper.EntityMapper;
import com.sensor.model.User;
import com.sensor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EntityMapper<ApplicationUserDetails, User> applicationUserDetailsMapper;
    @Autowired
    private EntityMapper<User, UserRequestDto> userRequestDtoMapper;

    @Override
    public ApplicationUserDetails findByLogin(String login) {
        User user = dao.findByLogin(login);
        return applicationUserDetailsMapper.map(user);
    }

    @Override
    public boolean checkUserCredentials(ApplicationUserDetails dto, String password) {
        if (!passwordEncoder.matches(password, dto.getPassword())) {
            throw new BadCredentialsException("Bad credentials at " + dto.getLogin() + " login");
        }
        return true;
    }

    @Override
    public User findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Long save(UserRequestDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return dao.save(userRequestDtoMapper.map(dto));
    }
}
