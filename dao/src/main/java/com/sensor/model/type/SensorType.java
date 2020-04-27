package com.sensor.model.type;

import com.sensor.constant.ApplicationConstant;
import com.sensor.exception.BadEnumValueException;
import lombok.Getter;

import java.util.Arrays;

public enum SensorType {
    PRESSURE(ApplicationConstant.PRESSURE),
    VOLTAGE(ApplicationConstant.VOLTAGE),
    TEMPERATURE(ApplicationConstant.TEMPERATURE),
    HUMIDITY(ApplicationConstant.HUMIDITY);

    @Getter
    private String type;

    SensorType(String type) {
        this.type = type;
    }

    public static SensorType typeValueOf(String type) {
        return Arrays.stream(SensorType.values())
                .filter(enumType -> enumType.getType().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new BadEnumValueException("Bad value for enum: " + type));
    }
}
