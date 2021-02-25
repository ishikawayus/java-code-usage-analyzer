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
    @Column(name = "class_id", nullable = false)
    private String classId;

    @Column(name = "module_id", nullable = false)
    private String moduleId;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "super_class_name", nullable = false)
    private String superClassName;
}
