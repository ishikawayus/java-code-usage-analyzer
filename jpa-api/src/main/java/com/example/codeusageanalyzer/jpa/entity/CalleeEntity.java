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
    @Column(name = "callee_id", nullable = false)
    private String calleeId;

    @Column(name = "method_id", nullable = false)
    private String methodId;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "method_name", nullable = false)
    private String methodName;

    @Column(name = "descriptor", nullable = false)
    private String descriptor;

    @Column(name = "invoke_dynamic", nullable = false)
    private boolean invokeDynamic;
}
