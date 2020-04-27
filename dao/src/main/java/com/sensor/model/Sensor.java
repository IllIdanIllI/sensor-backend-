package com.sensor.model;

import com.sensor.model.bridge.SensorTypeBridge;
import com.sensor.model.bridge.UnitTypeBridge;
import com.sensor.model.type.SensorType;
import com.sensor.model.type.UnitType;
import com.vladmihalcea.hibernate.type.range.PostgreSQLRangeType;
import com.vladmihalcea.hibernate.type.range.Range;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sensor")
@Data
@AttributeOverride(name = "id", column = @Column(columnDefinition = "integer"))
@TypeDef(
        typeClass = PostgreSQLRangeType.class,
        defaultForType = Range.class
)
@Indexed
public class Sensor implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensorIdSequencer")
    @SequenceGenerator(name = "sensorIdSequencer", sequenceName = "sensor_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    @Field
    private String name;

    @Column(nullable = false)
    @Field
    private String model;

    @Column
    private Range<Integer> range;

    @Column
    @Field
    private String location;

    @Column
    @Enumerated(EnumType.STRING)
    @Type(type = "com.sensor.converter.type.UnitEnumType")
    @Field(bridge = @FieldBridge(impl = UnitTypeBridge.class))
    private UnitType unit;

    @Column
    @Enumerated(EnumType.STRING)
    @Type(type = "com.sensor.converter.type.SensorEnumType")
    @Field(bridge = @FieldBridge(impl = SensorTypeBridge.class))
    private SensorType type;

    @Column
    @Field(termVector = TermVector.YES)
    private String description;
}
