package com.example.codeusageanalyzer.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "dependency")
public class DependencyEntity {

    @Id
    @Column(name = "dependency_id", nullable = false)
    private String dependencyId;

    @Column(name = "module_id", nullable = false)
    private String moduleId;

    @Column(name = "group_id", nullable = false)
    private String groupId;

    @Column(name = "artifact_id", nullable = false)
    private String artifactId;

    @Column(name = "version", nullable = false)
    private String version;
}
