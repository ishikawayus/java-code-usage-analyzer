/*
 * This file is generated by jOOQ.
 */
package com.example.codeusageanalyzer.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JModulePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String moduleId;
    private String artifactId;
    private String groupId;
    private String path;
    private String repositoryId;
    private String version;

    public JModulePojo() {}

    public JModulePojo(JModulePojo value) {
        this.moduleId = value.moduleId;
        this.artifactId = value.artifactId;
        this.groupId = value.groupId;
        this.path = value.path;
        this.repositoryId = value.repositoryId;
        this.version = value.version;
    }

    public JModulePojo(
        String moduleId,
        String artifactId,
        String groupId,
        String path,
        String repositoryId,
        String version
    ) {
        this.moduleId = moduleId;
        this.artifactId = artifactId;
        this.groupId = groupId;
        this.path = path;
        this.repositoryId = repositoryId;
        this.version = version;
    }

    /**
     * Getter for <code>MODULE.MODULE_ID</code>.
     */
    public String getModuleId() {
        return this.moduleId;
    }

    /**
     * Setter for <code>MODULE.MODULE_ID</code>.
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * Getter for <code>MODULE.ARTIFACT_ID</code>.
     */
    public String getArtifactId() {
        return this.artifactId;
    }

    /**
     * Setter for <code>MODULE.ARTIFACT_ID</code>.
     */
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    /**
     * Getter for <code>MODULE.GROUP_ID</code>.
     */
    public String getGroupId() {
        return this.groupId;
    }

    /**
     * Setter for <code>MODULE.GROUP_ID</code>.
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Getter for <code>MODULE.PATH</code>.
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Setter for <code>MODULE.PATH</code>.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Getter for <code>MODULE.REPOSITORY_ID</code>.
     */
    public String getRepositoryId() {
        return this.repositoryId;
    }

    /**
     * Setter for <code>MODULE.REPOSITORY_ID</code>.
     */
    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    /**
     * Getter for <code>MODULE.VERSION</code>.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Setter for <code>MODULE.VERSION</code>.
     */
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
