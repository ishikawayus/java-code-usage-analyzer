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
    private long repositoryId;

    @Column(nullable = false)
    private String url;
}
