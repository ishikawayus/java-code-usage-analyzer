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
    @Column(name = "interface_id", nullable = false)
    private String interfaceId;

    @Column(name = "class_id", nullable = false)
    private String classId;

    @Column(name = "interface_name", nullable = false)
    private String interfaceName;
}
