package com.sensor.mapper.sensor;

import com.sensor.dto.responce.SensorResponseDto;
import com.sensor.mapper.EntityMapper;
import com.sensor.model.Sensor;
import org.springframework.stereotype.Component;

@Component
public class SensorResponseDtoMapper extends EntityMapper<SensorResponseDto, Sensor> {

    private static final String DASH = "-";

    @Override
    public SensorResponseDto map(Sensor objectToMap) {
        String range = String.valueOf(objectToMap.getRange().lower()) +
                DASH +
                objectToMap.getRange().upper();
        return SensorResponseDto.builder()
                .name(objectToMap.getName())
                .model(objectToMap.getModel())
                .range(range)
                .location(objectToMap.getLocation())
                .unit(objectToMap.getUnit().getType())
                .type(objectToMap.getType().getType())
                .description(objectToMap.getDescription())
                .build();
    }
}
