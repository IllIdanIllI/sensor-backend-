package com.sensor.model.type;

import lombok.Getter;

public enum UserRole {
    ADMINISTRATOR("ADMINISTRATOR"), VIEWER("VIEWER");

    @Getter
    private String role;

    UserRole(String role) {
        this.role = role;
    }
}
