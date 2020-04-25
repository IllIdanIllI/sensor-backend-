package com.sensor.model;

import com.sensor.model.type.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(columnDefinition = "integer"))
public class User extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdSequencer")
    @SequenceGenerator(name = "userIdSequencer", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(nullable = false)
    @Access(AccessType.PROPERTY)
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "user_role")
    private UserRole role;
}
