package com.example.codeusageanalyzer.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "repository")
public class RepositoryEntity {

    @Id
    @Column(name = "repository_id", nullable = false)
    private String repositoryId;

    @Column(name = "url", nullable = false)
    private String url;
}
