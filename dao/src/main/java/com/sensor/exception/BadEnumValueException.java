package com.sensor.exception;

public class BadEnumValueException extends RuntimeException{
    public BadEnumValueException() {
    }

    public BadEnumValueException(String message) {
        super(message);
    }

    public BadEnumValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadEnumValueException(Throwable cause) {
        super(cause);
    }

    public BadEnumValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
