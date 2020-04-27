package com.sensor.controller;

import com.sensor.dto.details.ApplicationUserDetails;
import com.sensor.dto.request.AuthenticationRequestDto;
import com.sensor.dto.request.UserRequestDto;
import com.sensor.dto.responce.AuthenticationResponseDto;
import com.sensor.model.User;
import com.sensor.security.jwt.JwtUtil;
import com.sensor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Validated
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable Long id) {
        User user = service.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<AuthenticationResponseDto> signIn(@Valid @RequestBody AuthenticationRequestDto authRequest) {
        ApplicationUserDetails dto = service.findByLogin(authRequest.getLogin());
        if (service.checkUserCredentials(dto, authRequest.getPassword())) {
            String token = jwtUtil.generateToken(dto.getLogin());
            AuthenticationResponseDto response
                    = new AuthenticationResponseDto(token, dto.getRole());
            return ResponseEntity.ok().body(response);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = "/users")
    public ResponseEntity create(@Valid @RequestBody UserRequestDto dto, UriComponentsBuilder uriBuilder) {
        Long id = service.save(dto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/users/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
