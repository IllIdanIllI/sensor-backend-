package com.sensor.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorRequestDto implements Serializable {

    @NotBlank(message = "Name must be not empty")
    @Length(max = 30, message = "Name must be no longer than 30 characters")
    private String name;

    @NotBlank(message = "Model must be not empty")
    @Length(max = 15, message = "Model must be no longer than 15 characters")
    private String model;

    private Integer rangeFrom;

    private Integer rangeTo;

    @Length(max = 40, message = "Location must be no longer than 40 characters")
    private String location;

    @NotBlank(message = "Unit must be not empty")
    private String unit;

    @NotBlank(message = "Type must be not empty")
    private String type;

    @Length(max = 200, message = "Description must be no longer than 200 characters")
    private String description;
}
