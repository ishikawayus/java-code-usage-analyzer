/*
 * This file is generated by jOOQ.
 */
package com.example.codeusageanalyzer.jooq.tables.daos;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import com.example.codeusageanalyzer.jooq.tables.JCallee;
import com.example.codeusageanalyzer.jooq.tables.pojos.JCalleePojo;
import com.example.codeusageanalyzer.jooq.tables.records.JCalleeRecord;

/**
 * This class is generated by jOOQ.
 */
@Generated(value = { "http://www.jooq.org", "jOOQ version:3.9.6" }, comments = "This class is generated by jOOQ")
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JCalleeDao extends DAOImpl<JCalleeRecord, JCalleePojo, Long> {

    /**
     * Create a new JCalleeDao without any configuration
     */
    public JCalleeDao() {
        super(JCallee.CALLEE, JCalleePojo.class);
    }

    /**
     * Create a new JCalleeDao with an attached configuration
     */
    public JCalleeDao(Configuration configuration) {
        super(JCallee.CALLEE, JCalleePojo.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(JCalleePojo object) {
        return object.getCalleeId();
    }

    /**
     * Fetch records that have <code>CALLEE_ID IN (values)</code>
     */
    public List<JCalleePojo> fetchByJCalleeId(Long... values) {
        return fetch(JCallee.CALLEE.CALLEE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>CALLEE_ID = value</code>
     */
    public JCalleePojo fetchOneByJCalleeId(Long value) {
        return fetchOne(JCallee.CALLEE.CALLEE_ID, value);
    }

    /**
     * Fetch records that have <code>DESCRIPTOR IN (values)</code>
     */
    public List<JCalleePojo> fetchByJDescriptor(String... values) {
        return fetch(JCallee.CALLEE.DESCRIPTOR, values);
    }

    /**
     * Fetch records that have <code>INVOKE_DYNAMIC IN (values)</code>
     */
    public List<JCalleePojo> fetchByJInvokeDynamic(Boolean... values) {
        return fetch(JCallee.CALLEE.INVOKE_DYNAMIC, values);
    }

    /**
     * Fetch records that have <code>METHOD_ID IN (values)</code>
     */
    public List<JCalleePojo> fetchByJMethodId(Long... values) {
        return fetch(JCallee.CALLEE.METHOD_ID, values);
    }

    /**
     * Fetch records that have <code>METHOD_NAME IN (values)</code>
     */
    public List<JCalleePojo> fetchByJMethodName(String... values) {
        return fetch(JCallee.CALLEE.METHOD_NAME, values);
    }

    /**
     * Fetch records that have <code>OWNER_NAME IN (values)</code>
     */
    public List<JCalleePojo> fetchByJOwnerName(String... values) {
        return fetch(JCallee.CALLEE.OWNER_NAME, values);
    }
}