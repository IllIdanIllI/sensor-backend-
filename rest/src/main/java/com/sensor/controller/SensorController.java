package com.sensor.controller;

import com.sensor.dto.request.SensorRequestDto;
import com.sensor.dto.responce.PaginationResponseDto;
import com.sensor.dto.responce.SensorResponseDto;
import com.sensor.service.SensorService;
import com.sensor.util.encoder.ApplicationCoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;

import static com.sensor.constant.ApplicationConstant.VALIDATION_MAX_LIMIT;
import static com.sensor.constant.ApplicationConstant.VALIDATION_MAX_PAGE;


@CrossOrigin
@RestController
@Validated
public class SensorController {

    @Autowired
    private SensorService service;
    @Autowired
    private ApplicationCoder<Long, String> coder;

    @GetMapping(value = "/sensors/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('VIEWER')")
    public ResponseEntity<SensorResponseDto> getUsers(@PathVariable String id) {
        Long realId = coder.decode(id);
        SensorResponseDto responseDto = service.findById(realId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/sensors/search")
    @PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('VIEWER')")
    public ResponseEntity<PaginationResponseDto<SensorResponseDto>> searchByCriteria(@RequestParam("phrase") String searchCriteria,
                                                                                     @RequestParam @Max(value = 1000, message = VALIDATION_MAX_PAGE)
                                                                                             Integer currentPage,
                                                                                     @RequestParam @Max(value = 50, message = VALIDATION_MAX_LIMIT)
                                                                                             Integer limit) {
        PaginationResponseDto<SensorResponseDto> responseDtos = service.findBySearchCriteria(searchCriteria, currentPage, limit);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/sensors")
    @PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('VIEWER')")
    public ResponseEntity<PaginationResponseDto<SensorResponseDto>> getAll(@RequestParam @Max(value = 1000, message = VALIDATION_MAX_PAGE)
                                                                           @Valid Integer currentPage,
                                                                           @RequestParam @Max(value = 50, message = VALIDATION_MAX_LIMIT)
                                                                           @Valid Integer limit) {
        PaginationResponseDto<SensorResponseDto> responseDtos = service.findEntitiesPagination(currentPage, limit);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @PutMapping(value = "/sensors/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity updateById(@PathVariable String id, @Valid @RequestBody SensorRequestDto dto) {
        Long realId = coder.decode(id);
        service.update(realId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/sensors")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity create(@Valid @RequestBody SensorRequestDto dto, UriComponentsBuilder uriBuilder) {
        Long id = service.save(dto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/sensors/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/sensors/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity deleteById(@PathVariable String id) {
        Long realId = coder.decode(id);
        service.delete(realId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
