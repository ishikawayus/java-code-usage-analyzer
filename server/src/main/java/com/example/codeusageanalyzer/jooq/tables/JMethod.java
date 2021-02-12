/*
 * This file is generated by jOOQ.
 */
package com.example.codeusageanalyzer.jooq.tables;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;

import com.example.codeusageanalyzer.jooq.JPublic;
import com.example.codeusageanalyzer.jooq.Keys;
import com.example.codeusageanalyzer.jooq.tables.records.JMethodRecord;

/**
 * This class is generated by jOOQ.
 */
@Generated(value = { "http://www.jooq.org", "jOOQ version:3.9.6" }, comments = "This class is generated by jOOQ")
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JMethod extends TableImpl<JMethodRecord> {

    private static final long serialVersionUID = 1572869384;

    /**
     * The reference instance of <code>PUBLIC.METHOD</code>
     */
    public static final JMethod METHOD = new JMethod();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JMethodRecord> getRecordType() {
        return JMethodRecord.class;
    }

    /**
     * The column <code>PUBLIC.METHOD.METHOD_ID</code>.
     */
    public final TableField<JMethodRecord, Long> METHOD_ID = createField("METHOD_ID",
            org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.METHOD.CLASS_ID</code>.
     */
    public final TableField<JMethodRecord, Long> CLASS_ID = createField("CLASS_ID",
            org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.METHOD.DESCRIPTOR</code>.
     */
    public final TableField<JMethodRecord, String> DESCRIPTOR = createField("DESCRIPTOR",
            org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.METHOD.METHOD_NAME</code>.
     */
    public final TableField<JMethodRecord, String> METHOD_NAME = createField("METHOD_NAME",
            org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.METHOD</code> table reference
     */
    public JMethod() {
        this("METHOD", null);
    }

    /**
     * Create an aliased <code>PUBLIC.METHOD</code> table reference
     */
    public JMethod(String alias) {
        this(alias, METHOD);
    }

    private JMethod(String alias, Table<JMethodRecord> aliased) {
        this(alias, aliased, null);
    }

    private JMethod(String alias, Table<JMethodRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return JPublic.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<JMethodRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_8;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<JMethodRecord>> getKeys() {
        return Arrays.<UniqueKey<JMethodRecord>>asList(Keys.CONSTRAINT_8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JMethod as(String alias) {
        return new JMethod(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JMethod rename(String name) {
        return new JMethod(name, null);
    }
}
