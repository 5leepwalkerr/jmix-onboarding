package com.company.onboarding.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "DEPARTAMENT", indexes = {
        @Index(name = "IDX_DEPARTAMENT_HR_MANGER", columnList = "hr_manger_Id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "IDX_DEPARTAMENT_UNQ_NAME", columnNames = {"NAME"})
})
@Entity
public class Departament {
    public Departament(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "name", length = 50)
    @NotNull
    private String name;
}