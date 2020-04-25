package com.sensor.model.bridge;

import com.sensor.model.type.SensorType;
import org.hibernate.search.bridge.StringBridge;

import java.util.Arrays;

public class SensorTypeBridge implements StringBridge {

    @Override
    public String objectToString(Object sensorType) {
        return Arrays.stream(SensorType.values())
                .filter(type -> type.getType().toLowerCase()
                        .contains(sensorType.toString().toLowerCase()))
                .findFirst().map(SensorType::getType).orElse(null);
    }
}
