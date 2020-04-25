package com.sensor.model;

import com.sensor.converter.SensorTypeConverter;
import com.sensor.converter.UnitTypeConverter;
import com.sensor.model.bridge.SensorTypeBridge;
import com.sensor.model.bridge.UnitTypeBridge;
import com.sensor.model.type.SensorType;
import com.sensor.model.type.UnitType;
import com.vladmihalcea.hibernate.type.range.PostgreSQLRangeType;
import com.vladmihalcea.hibernate.type.range.Range;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;

@Entity
@Table(name = "sensor")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(columnDefinition = "integer"))
@TypeDef(
        typeClass = PostgreSQLRangeType.class,
        defaultForType = Range.class
)
@Indexed
public class Sensor extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensorIdSequencer")
    @SequenceGenerator(name = "sensorIdSequencer", sequenceName = "sensor_id_seq", allocationSize = 1)
    @Column(nullable = false)
    @Access(AccessType.PROPERTY)
    @Override
    public Long getId() {
        return super.getId();
    }

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
    @Field(bridge = @FieldBridge(impl = UnitTypeBridge.class))
    @Convert(converter = UnitTypeConverter.class)
    private UnitType unit;

    @Column
    @Field(bridge = @FieldBridge(impl = SensorTypeBridge.class))
    @Convert(converter = SensorTypeConverter.class)
    private SensorType type;

    @Column
    @Field(termVector = TermVector.YES)
    private String description;
}
