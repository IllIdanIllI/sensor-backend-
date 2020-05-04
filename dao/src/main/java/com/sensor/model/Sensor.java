package com.sensor.model;

import com.sensor.model.bridge.SensorTypeBridge;
import com.sensor.model.bridge.UnitTypeBridge;
import com.sensor.model.type.SensorType;
import com.sensor.model.type.UnitType;
import com.vladmihalcea.hibernate.type.range.PostgreSQLRangeType;
import com.vladmihalcea.hibernate.type.range.Range;
import lombok.Data;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

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
@AnalyzerDef(name = "edgeNgram",
        tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(
                        factory = EdgeNGramFilterFactory.class,
                        params = {
                                @Parameter(name = "minGramSize", value = "1"),
                                @Parameter(name = "maxGramSize", value = "2")
                        }
                )
        })
public class Sensor implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensorIdSequencer")
    @SequenceGenerator(name = "sensorIdSequencer", sequenceName = "sensor_id_seq", allocationSize = 1)
    private Long id;

    @Analyzer(definition = "edgeNgram")
    @Column(nullable = false)
    @Field
    private String name;

    @Analyzer(definition = "edgeNgram")
    @Column(nullable = false)
    @Field
    private String model;

    @Column
    private Range<Integer> range;

    @Analyzer(definition = "edgeNgram")
    @Column
    @Field
    private String location;

    @Analyzer(definition = "edgeNgram")
    @Column
    @Enumerated(EnumType.STRING)
    @Type(type = "com.sensor.converter.type.UnitEnumType")
    @Field(bridge = @FieldBridge(impl = UnitTypeBridge.class))
    private UnitType unit;

    @Analyzer(definition = "edgeNgram")
    @Column
    @Enumerated(EnumType.STRING)
    @Type(type = "com.sensor.converter.type.SensorEnumType")
    @Field(bridge = @FieldBridge(impl = SensorTypeBridge.class))
    private SensorType type;

    @Analyzer(definition = "edgeNgram")
    @Column
    @Field(termVector = TermVector.YES)
    private String description;
}
