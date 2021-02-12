/*
 * This file is generated by jOOQ.
 */
package com.example.codeusageanalyzer.jooq.tables.pojos;

import java.io.Serializable;

import javax.annotation.Generated;

/**
 * This class is generated by jOOQ.
 */
@Generated(value = { "http://www.jooq.org", "jOOQ version:3.9.6" }, comments = "This class is generated by jOOQ")
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JModulePojo implements Serializable {

    private static final long serialVersionUID = 2125488009;

    private Long moduleId;

    private String artifactId;

    private String groupId;

    private String path;

    private Long repositoryId;

    private String version;

    public JModulePojo() {
    }

    public JModulePojo(JModulePojo value) {
        this.moduleId = value.moduleId;
        this.artifactId = value.artifactId;
        this.groupId = value.groupId;
        this.path = value.path;
        this.repositoryId = value.repositoryId;
        this.version = value.version;
    }

    public JModulePojo(Long moduleId, String artifactId, String groupId, String path, Long repositoryId,
            String version) {
        this.moduleId = moduleId;
        this.artifactId = artifactId;
        this.groupId = groupId;
        this.path = path;
        this.repositoryId = repositoryId;
        this.version = version;
    }

    public Long getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getArtifactId() {
        return this.artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getRepositoryId() {
        return this.repositoryId;
    }

    public void setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("JModulePojo (");

        sb.append(moduleId);
        sb.append(", ").append(artifactId);
        sb.append(", ").append(groupId);
        sb.append(", ").append(path);
        sb.append(", ").append(repositoryId);
        sb.append(", ").append(version);

        sb.append(")");
        return sb.toString();
    }
}
