package com.sensor.service.impl;

import com.sensor.dao.SensorDao;
import com.sensor.dto.request.SensorRequestDto;
import com.sensor.dto.responce.PaginationResponseDto;
import com.sensor.dto.responce.SensorResponseDto;
import com.sensor.exception.SensorValidationException;
import com.sensor.mapper.EntityMapper;
import com.sensor.model.Sensor;
import com.sensor.model.transport.PaginateEntity;
import com.sensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorDao dao;
    @Autowired
    private EntityMapper<SensorResponseDto, Sensor> sensorResponseDtoMapper;
    @Autowired
    private EntityMapper<Sensor, SensorRequestDto> sensorRequestDtoMapper;

    @Override
    public SensorResponseDto findById(long id) {
        Sensor sensor = dao.findById(id);
        return sensorResponseDtoMapper.map(sensor);
    }

    @Override
    @Transactional
    public PaginationResponseDto<SensorResponseDto> findBySearchCriteria(String searchCriteria, int currentPage, int limit) {
        PaginateEntity<Sensor> entity = dao.findBySearchCriteria(searchCriteria, currentPage, limit);
        List<Sensor> sensors = entity.getEntities();
        List<SensorResponseDto> dtos = sensors.stream()
                .map(sensor -> sensorResponseDtoMapper.map(sensor))
                .collect(Collectors.toList());
        long recordsAmount = entity.getEntitiesAmount();
        long pageAmount = (recordsAmount % limit == 0)
                ? recordsAmount / limit
                : recordsAmount / limit + 1;
        return new PaginationResponseDto(dtos, pageAmount);
    }

    @Override
    public Long save(SensorRequestDto dto) {
        if (dto.getRangeFrom() > dto.getRangeTo()) {
            throw new SensorValidationException("Invalid range, field \'from\' lower that field \'to\'");
        }
        return dao.save(sensorRequestDtoMapper.map(dto));
    }

    @Override
    public void update(Long id, SensorRequestDto dto) {
        if (dto.getRangeFrom() > dto.getRangeTo()) {
            throw new SensorValidationException("Invalid range, field \'from\' lower that field \'to\'");
        }
        Sensor sensor = sensorRequestDtoMapper.map(dto);
        sensor.setId(id);
        dao.update(sensor);
    }

    @Override
    public void delete(Long id) {
        Sensor sensor = new Sensor();
        sensor.setId(id);
        dao.delete(sensor);
    }


    @Override
    @Transactional
    public PaginationResponseDto<SensorResponseDto> findEntitiesPagination(int currentPage, int limit) {
        List<Sensor> sensors = dao.findEntitiesPagination(currentPage, limit);
        List<SensorResponseDto> dtos = sensors.stream()
                .map(sensor -> sensorResponseDtoMapper.map(sensor))
                .collect(Collectors.toList());
        long recordsAmount = dao.findEntitiesAmountPagination();
        long pageAmount = (recordsAmount % limit == 0)
                ? recordsAmount / limit
                : recordsAmount / limit + 1;
        return new PaginationResponseDto(dtos, pageAmount);
    }
}
