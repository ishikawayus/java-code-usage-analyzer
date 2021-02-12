package com.example.codeusageanalyzer.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "callee")
public class CalleeEntity {

    @Id
    private long calleeId;

    @Column(nullable = false)
    private long methodId;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false)
    private String methodName;

    @Column(nullable = false)
    private String descriptor;
}
