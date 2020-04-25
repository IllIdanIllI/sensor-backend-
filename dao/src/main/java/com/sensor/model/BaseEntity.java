package com.sensor.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    protected Long id;
}



