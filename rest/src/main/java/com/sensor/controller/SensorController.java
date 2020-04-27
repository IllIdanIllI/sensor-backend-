package com.sensor.controller;

import com.sensor.dto.request.SensorRequestDto;
import com.sensor.dto.responce.PaginationResponseDto;
import com.sensor.dto.responce.SensorResponseDto;
import com.sensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Validated
@CrossOrigin
@RestController
public class SensorController {

    @Autowired
    private SensorService service;

    @GetMapping(value = "/sensors/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('VIEWER')")
    public ResponseEntity<SensorResponseDto> getUsers(@PathVariable Long id) {
        SensorResponseDto responseDto = service.findById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/sensors/search")
    @PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('VIEWER')")
    public ResponseEntity<PaginationResponseDto<SensorResponseDto>> searchByCriteria(@RequestParam("phrase") String searchCriteria,
                                                                                     @RequestParam Integer currentPage,
                                                                                     @RequestParam Integer limit) {
        PaginationResponseDto<SensorResponseDto> responseDtos = service.findBySearchCriteria(searchCriteria, currentPage, limit);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/sensors")
    @PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('VIEWER')")
    public ResponseEntity<PaginationResponseDto<SensorResponseDto>> getAll(@RequestParam Integer currentPage,
                                                                           @RequestParam Integer limit) {
        PaginationResponseDto<SensorResponseDto> responseDtos = service.findEntitiesPagination(currentPage, limit);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @PutMapping(value = "/sensors/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity updateById(@PathVariable Long id, @Valid @RequestBody SensorRequestDto dto) {
        service.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/sensors")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity create(@Valid @RequestBody SensorRequestDto dto, Authentication authentication, UriComponentsBuilder uriBuilder) {
        Long id = service.save(dto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/sensors/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/sensors/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity deleteById(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
