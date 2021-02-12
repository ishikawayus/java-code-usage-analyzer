package com.example.codeusageanalyzer.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "module")
public class ModuleEntity {

    @Id
    private long moduleId;

    @Column(nullable = false)
    private long repositoryId;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String groupId;

    @Column(nullable = false)
    private String artifactId;

    @Column(nullable = false)
    private String version;
}
