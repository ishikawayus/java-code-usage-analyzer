/*
 * This file is generated by jOOQ.
 */
package com.example.codeusageanalyzer.jooq.tables;


import com.example.codeusageanalyzer.jooq.DefaultSchema;
import com.example.codeusageanalyzer.jooq.Keys;
import com.example.codeusageanalyzer.jooq.tables.records.JClassRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
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
public class JClass extends TableImpl<JClassRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>CLASS</code>
     */
    public static final JClass CLASS = new JClass();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JClassRecord> getRecordType() {
        return JClassRecord.class;
    }

    /**
     * The column <code>CLASS.CLASS_ID</code>.
     */
    public final TableField<JClassRecord, String> CLASS_ID = createField(DSL.name("CLASS_ID"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>CLASS.CLASS_NAME</code>.
     */
    public final TableField<JClassRecord, String> CLASS_NAME = createField(DSL.name("CLASS_NAME"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>CLASS.MODULE_ID</code>.
     */
    public final TableField<JClassRecord, String> MODULE_ID = createField(DSL.name("MODULE_ID"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>CLASS.SUPER_CLASS_NAME</code>.
     */
    public final TableField<JClassRecord, String> SUPER_CLASS_NAME = createField(DSL.name("SUPER_CLASS_NAME"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private JClass(Name alias, Table<JClassRecord> aliased) {
        this(alias, aliased, null);
    }

    private JClass(Name alias, Table<JClassRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>CLASS</code> table reference
     */
    public JClass(String alias) {
        this(DSL.name(alias), CLASS);
    }

    /**
     * Create an aliased <code>CLASS</code> table reference
     */
    public JClass(Name alias) {
        this(alias, CLASS);
    }

    /**
     * Create a <code>CLASS</code> table reference
     */
    public JClass() {
        this(DSL.name("CLASS"), null);
    }

    public <O extends Record> JClass(Table<O> child, ForeignKey<O, JClassRecord> key) {
        super(child, key, CLASS);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<JClassRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_3;
    }

    @Override
    public List<UniqueKey<JClassRecord>> getKeys() {
        return Arrays.<UniqueKey<JClassRecord>>asList(Keys.CONSTRAINT_3);
    }

    @Override
    public JClass as(String alias) {
        return new JClass(DSL.name(alias), this);
    }

    @Override
    public JClass as(Name alias) {
        return new JClass(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JClass rename(String name) {
        return new JClass(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JClass rename(Name name) {
        return new JClass(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
