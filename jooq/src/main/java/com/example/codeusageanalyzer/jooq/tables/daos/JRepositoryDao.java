/*
 * This file is generated by jOOQ.
*/
package com.example.codeusageanalyzer.jooq.tables.daos;


import com.example.codeusageanalyzer.jooq.tables.JRepository;
import com.example.codeusageanalyzer.jooq.tables.pojos.JRepositoryPojo;
import com.example.codeusageanalyzer.jooq.tables.records.JRepositoryRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JRepositoryDao extends DAOImpl<JRepositoryRecord, JRepositoryPojo, String> {

    /**
     * Create a new JRepositoryDao without any configuration
     */
    public JRepositoryDao() {
        super(JRepository.REPOSITORY, JRepositoryPojo.class);
    }

    /**
     * Create a new JRepositoryDao with an attached configuration
     */
    public JRepositoryDao(Configuration configuration) {
        super(JRepository.REPOSITORY, JRepositoryPojo.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(JRepositoryPojo object) {
        return object.getRepositoryId();
    }

    /**
     * Fetch records that have <code>REPOSITORY_ID IN (values)</code>
     */
    public List<JRepositoryPojo> fetchByJRepositoryId(String... values) {
        return fetch(JRepository.REPOSITORY.REPOSITORY_ID, values);
    }

    /**
     * Fetch a unique record that has <code>REPOSITORY_ID = value</code>
     */
    public JRepositoryPojo fetchOneByJRepositoryId(String value) {
        return fetchOne(JRepository.REPOSITORY.REPOSITORY_ID, value);
    }

    /**
     * Fetch records that have <code>URL IN (values)</code>
     */
    public List<JRepositoryPojo> fetchByJUrl(String... values) {
        return fetch(JRepository.REPOSITORY.URL, values);
    }
}