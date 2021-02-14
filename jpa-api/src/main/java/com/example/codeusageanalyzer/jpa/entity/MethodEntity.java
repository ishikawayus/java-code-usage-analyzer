package com.example.codeusageanalyzer.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "method")
public class MethodEntity {

    @Id
    @Column(name = "method_id", nullable = false)
    private String methodId;

    @Column(name = "class_id", nullable = false)
    private String classId;

    @Column(name = "method_name", nullable = false)
    private String methodName;

    @Column(name = "descriptor", nullable = false)
    private String descriptor;
}
