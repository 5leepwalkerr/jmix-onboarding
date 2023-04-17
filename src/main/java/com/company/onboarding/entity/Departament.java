package com.company.onboarding.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

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
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "name", length = 50)
    @NotNull
    private String name;

    @JoinColumn(name = "hr_manger_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User hrManager;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    public void setHrManager(User hrManager) {
        this.hrManager = hrManager;
    }

    public User getHrManager() {
        return hrManager;
    }

    public User getHtManager() {
        return hrManager;
    }

    public void setHtManager(User htManager) {
        this.hrManager = htManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}