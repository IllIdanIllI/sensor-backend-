package com.sensor.model.type;

import com.sensor.constant.ApplicationConstant;
import lombok.Getter;

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
}
