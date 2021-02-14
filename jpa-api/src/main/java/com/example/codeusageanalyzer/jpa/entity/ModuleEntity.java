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
    @Column(name = "module_id", nullable = false)
    private String moduleId;

    @Column(name = "repository_id", nullable = false)
    private String repositoryId;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "group_id", nullable = false)
    private String groupId;

    @Column(name = "artifact_id", nullable = false)
    private String artifactId;

    @Column(name = "version", nullable = false)
    private String version;
}
