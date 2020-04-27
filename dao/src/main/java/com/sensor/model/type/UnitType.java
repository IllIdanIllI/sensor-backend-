package com.sensor.model.type;

import com.sensor.constant.ApplicationConstant;
import com.sensor.exception.BadEnumValueException;
import lombok.Getter;

import java.util.Arrays;

public enum UnitType {
    BAR(ApplicationConstant.BAR_UNIT),
    VOLTAGE(ApplicationConstant.VOLTAGE_UNIT),
    CELSIUS(ApplicationConstant.CELSIUS_UNIT),
    PERCENT(ApplicationConstant.PERCENT_UNIT);

    @Getter
    private String type;

    UnitType(String type) {
        this.type = type;
    }

    public static UnitType typeValueOf(String type) {
        return Arrays.stream(UnitType.values())
                .filter(enumType -> enumType.getType().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new BadEnumValueException("Bad value for enum: " + type));
    }
}
