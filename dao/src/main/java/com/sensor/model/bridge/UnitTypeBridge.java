package com.sensor.model.bridge;

import com.sensor.model.type.UnitType;
import org.hibernate.search.bridge.StringBridge;

import java.util.Arrays;

public class UnitTypeBridge implements StringBridge {

    @Override
    public String objectToString(Object unitType) {
        return Arrays.stream(UnitType.values())
                .filter(type -> type.getType().toLowerCase()
                        .contains(unitType.toString().toLowerCase()))
                .findFirst().map(UnitType::getType).orElse(null);
    }
}
