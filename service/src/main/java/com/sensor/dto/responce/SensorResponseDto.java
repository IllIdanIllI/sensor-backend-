package com.sensor.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorResponseDto implements Serializable {

    private String id;

    private String name;

    private String model;

    private String range;

    private String location;

    private String unit;

    private String type;

    private String description;
}
