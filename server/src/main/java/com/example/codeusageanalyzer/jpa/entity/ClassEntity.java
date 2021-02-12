package com.example.codeusageanalyzer.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "class")
public class ClassEntity {

    @Id
    @GeneratedValue
    private long classId;

    @Column(nullable = false)
    private long moduleId;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String className;

    @Column(nullable = false)
    private String superClassName;
}
