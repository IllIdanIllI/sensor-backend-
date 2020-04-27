package com.sensor.model;

import com.sensor.model.type.UserRole;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

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
