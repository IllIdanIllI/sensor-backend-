package com.sensor.service;

import com.sensor.dto.request.SensorRequestDto;
import com.sensor.dto.responce.PaginationResponseDto;
import com.sensor.dto.responce.SensorResponseDto;

public interface SensorService {
    SensorResponseDto findById(long id);

    PaginationResponseDto<SensorResponseDto> findBySearchCriteria(String searchCriteria, int currentPage, int limit);

    Long save(SensorRequestDto entity);

    void update(Long id, SensorRequestDto entity);

    void delete(Long id);

    PaginationResponseDto findEntitiesPagination(int currentPage, int limit);
}
