package com.sensor.mapper.sensor;

import com.sensor.dto.request.SensorRequestDto;
import com.sensor.mapper.EntityMapper;
import com.sensor.model.Sensor;
import com.sensor.model.type.SensorType;
import com.sensor.model.type.UnitType;
import com.vladmihalcea.hibernate.type.range.Range;
import org.springframework.stereotype.Component;

@Component
public class SensorRequestDtoMapper extends EntityMapper<Sensor, SensorRequestDto> {

    @Override
    public Sensor map(SensorRequestDto dto) {
        Sensor sensor = new Sensor();
        sensor.setName(dto.getName());
        sensor.setModel(dto.getModel());
        sensor.setRange(Range.closed(dto.getRangeFrom(), dto.getRangeTo()));
        sensor.setLocation(dto.getLocation());
        sensor.setUnit(UnitType.typeValueOf(dto.getUnit()));
        sensor.setType(SensorType.typeValueOf(dto.getType()));
        sensor.setDescription(dto.getDescription());
        return sensor;
    }
}
