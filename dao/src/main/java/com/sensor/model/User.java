package com.sensor.model;

import com.sensor.model.type.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode
//@EqualsAndHashCode(callSuper = true)
//@AttributeOverride(name = "id", column = @Column(columnDefinition = "integer"))
public class User implements Serializable {

//    @Override
//    @Access(AccessType.PROPERTY)
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdSequencer")
//    @SequenceGenerator(name = "userIdSequencer", sequenceName = "user_id_seq", allocationSize = 1)
//    public Long getId() {
//        return super.getId();
//    }


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdSequencer")
    @SequenceGenerator(name = "userIdSequencer", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    @Type(type = "com.sensor.converter.type.UserRoleType")
    private UserRole role;

}
