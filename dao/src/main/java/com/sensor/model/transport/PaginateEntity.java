package com.sensor.model.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginateEntity<T extends Serializable> implements Serializable {
    private List<T> entities;

    private long entitiesAmount;

}
