package com.sensor.converter;

import com.sensor.model.type.UnitType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.sensor.constant.ApplicationConstant.*;

@Converter
public class UnitTypeConverter implements AttributeConverter<UnitType, String> {

    @Override
    public String convertToDatabaseColumn(UnitType attribute) {
        if (attribute == null) {
            return null;
        }
        switch (attribute) {
            case BAR:
                return UnitType.BAR.getType();
            case CELSIUS:
                return UnitType.CELSIUS.getType();
            case PERCENT:
                return UnitType.PERCENT.getType();
            case VOLTAGE:
                return UnitType.VOLTAGE.getType();
            default:
                throw new IllegalArgumentException(attribute + " not supported type!");
        }
    }

    @Override
    public UnitType convertToEntityAttribute(String line) {
        if (line == null) {
            return null;
        }
        switch (line) {
            case BAR_UNIT:
                return UnitType.BAR;
            case VOLTAGE_UNIT:
                return UnitType.VOLTAGE;
            case CELSIUS_UNIT:
                return UnitType.CELSIUS;
            case PERCENT_UNIT:
                return UnitType.PERCENT;
            default:
                throw new IllegalArgumentException(line + " not supported type! ");
        }
    }
}
