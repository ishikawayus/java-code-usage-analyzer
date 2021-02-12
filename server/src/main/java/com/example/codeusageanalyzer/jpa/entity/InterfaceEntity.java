package com.example.codeusageanalyzer.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "interface")
public class InterfaceEntity {

    @Id
    private long interfaceId;

    @Column(nullable = false)
    private long classId;

    @Column(nullable = false)
    private String interfaceName;
}
