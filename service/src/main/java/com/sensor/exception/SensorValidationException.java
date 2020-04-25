package com.sensor.exception;

public class SensorValidationException extends RuntimeException{
    public SensorValidationException() {
        super();
    }

    public SensorValidationException(String message) {
        super(message);
    }

    public SensorValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SensorValidationException(Throwable cause) {
        super(cause);
    }

    protected SensorValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
