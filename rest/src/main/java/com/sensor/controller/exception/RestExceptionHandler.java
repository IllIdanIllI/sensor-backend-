package com.sensor.controller.exception;

import com.sensor.dto.details.ErrorDetails;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sensor.constant.ApplicationConstant.*;

@RestController
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CREDENTIALS_EXCEPTION = "CredentialsException";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> mapMessageErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (fieldError, fieldError2) -> fieldError));
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(STATUS, status.value());
        body.put(ERRORS, mapMessageErrors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorDetails> handleException(RuntimeException e, WebRequest request) {
        LocalDateTime currentTime = LocalDateTime.now();
        ErrorDetails errorDetails = new ErrorDetails(currentTime, e.getMessage(), e.toString());
        switch (e.getClass().getSimpleName()) {
            case CREDENTIALS_EXCEPTION:
                return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
            default:
                return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }
    }
}
