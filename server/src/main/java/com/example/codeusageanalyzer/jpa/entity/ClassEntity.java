package com.example.codeusageanalyzer.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "class")
public class ClassEntity {

    @Id
    private long classId;

    @Column(nullable = false)
    private long moduleId;

    @Column(nullable = false)
    private String className;

    @Column(nullable = false)
    private String superClassName;
}