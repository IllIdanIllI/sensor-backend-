package com.sensor.converter;

import com.sensor.model.type.SensorType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.sensor.constant.ApplicationConstant.*;

@Converter
public class SensorTypeConverter implements AttributeConverter<SensorType, String> {

    @Override
    public String convertToDatabaseColumn(SensorType attribute) {
        if (attribute == null) {
            return null;
        }
        switch (attribute) {
            case HUMIDITY:
                return SensorType.HUMIDITY.getType();
            case TEMPERATURE:
                return SensorType.TEMPERATURE.getType();
            case PRESSURE:
                return SensorType.PRESSURE.getType();
            case VOLTAGE:
                return SensorType.VOLTAGE.getType();
            default:
                throw new IllegalArgumentException(attribute + " not supported type!");
        }
    }

    @Override
    public SensorType convertToEntityAttribute(String line) {
        if (line == null) {
            return null;
        }
        switch (line) {
            case PRESSURE:
                return SensorType.PRESSURE;
            case VOLTAGE:
                return SensorType.VOLTAGE;
            case TEMPERATURE:
                return SensorType.TEMPERATURE;
            case HUMIDITY:
                return SensorType.HUMIDITY;
            default:
                throw new IllegalArgumentException(line + " not supported type! ");
        }
    }
}
