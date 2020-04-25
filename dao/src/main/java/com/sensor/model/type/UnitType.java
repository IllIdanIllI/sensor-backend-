package com.sensor.model.type;

import com.sensor.constant.ApplicationConstant;
import lombok.Getter;

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
}
