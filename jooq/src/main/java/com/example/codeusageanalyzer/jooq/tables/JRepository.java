/*
 * This file is generated by jOOQ.
 */
package com.example.codeusageanalyzer.jooq.tables;


import com.example.codeusageanalyzer.jooq.DefaultSchema;
import com.example.codeusageanalyzer.jooq.Keys;
import com.example.codeusageanalyzer.jooq.tables.records.JRepositoryRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JRepository extends TableImpl<JRepositoryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>REPOSITORY</code>
     */
    public static final JRepository REPOSITORY = new JRepository();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JRepositoryRecord> getRecordType() {
        return JRepositoryRecord.class;
    }

    /**
     * The column <code>REPOSITORY.REPOSITORY_ID</code>.
     */
    public final TableField<JRepositoryRecord, String> REPOSITORY_ID = createField(DSL.name("REPOSITORY_ID"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>REPOSITORY.URL</code>.
     */
    public final TableField<JRepositoryRecord, String> URL = createField(DSL.name("URL"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private JRepository(Name alias, Table<JRepositoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private JRepository(Name alias, Table<JRepositoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>REPOSITORY</code> table reference
     */
    public JRepository(String alias) {
        this(DSL.name(alias), REPOSITORY);
    }

    /**
     * Create an aliased <code>REPOSITORY</code> table reference
     */
    public JRepository(Name alias) {
        this(alias, REPOSITORY);
    }

    /**
     * Create a <code>REPOSITORY</code> table reference
     */
    public JRepository() {
        this(DSL.name("REPOSITORY"), null);
    }

    public <O extends Record> JRepository(Table<O> child, ForeignKey<O, JRepositoryRecord> key) {
        super(child, key, REPOSITORY);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<JRepositoryRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_9;
    }

    @Override
    public List<UniqueKey<JRepositoryRecord>> getKeys() {
        return Arrays.<UniqueKey<JRepositoryRecord>>asList(Keys.CONSTRAINT_9);
    }

    @Override
    public JRepository as(String alias) {
        return new JRepository(DSL.name(alias), this);
    }

    @Override
    public JRepository as(Name alias) {
        return new JRepository(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JRepository rename(String name) {
        return new JRepository(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JRepository rename(Name name) {
        return new JRepository(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
