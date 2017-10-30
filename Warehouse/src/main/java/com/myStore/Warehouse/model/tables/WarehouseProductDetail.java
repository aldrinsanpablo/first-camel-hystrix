/*
 * This file is generated by jOOQ.
*/
package com.myStore.Warehouse.model.tables;


import com.myStore.Warehouse.model.Keys;
import com.myStore.Warehouse.model.Public;
import com.myStore.Warehouse.model.tables.records.WarehouseProductDetailRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class WarehouseProductDetail extends TableImpl<WarehouseProductDetailRecord> {

    private static final long serialVersionUID = 974756511;

    /**
     * The reference instance of <code>PUBLIC.WAREHOUSE_PRODUCT_DETAIL</code>
     */
    public static final WarehouseProductDetail WAREHOUSE_PRODUCT_DETAIL = new WarehouseProductDetail();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WarehouseProductDetailRecord> getRecordType() {
        return WarehouseProductDetailRecord.class;
    }

    /**
     * The column <code>PUBLIC.WAREHOUSE_PRODUCT_DETAIL.ID</code>.
     */
    public final TableField<WarehouseProductDetailRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.WAREHOUSE_PRODUCT_DETAIL.PRODUCT_ID</code>.
     */
    public final TableField<WarehouseProductDetailRecord, Integer> PRODUCT_ID = createField("PRODUCT_ID", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.WAREHOUSE_PRODUCT_DETAIL.AVAILABLE_STOCKS</code>.
     */
    public final TableField<WarehouseProductDetailRecord, Integer> AVAILABLE_STOCKS = createField("AVAILABLE_STOCKS", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.WAREHOUSE_PRODUCT_DETAIL.PRICE</code>.
     */
    public final TableField<WarehouseProductDetailRecord, Double> PRICE = createField("PRICE", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * Create a <code>PUBLIC.WAREHOUSE_PRODUCT_DETAIL</code> table reference
     */
    public WarehouseProductDetail() {
        this("WAREHOUSE_PRODUCT_DETAIL", null);
    }

    /**
     * Create an aliased <code>PUBLIC.WAREHOUSE_PRODUCT_DETAIL</code> table reference
     */
    public WarehouseProductDetail(String alias) {
        this(alias, WAREHOUSE_PRODUCT_DETAIL);
    }

    private WarehouseProductDetail(String alias, Table<WarehouseProductDetailRecord> aliased) {
        this(alias, aliased, null);
    }

    private WarehouseProductDetail(String alias, Table<WarehouseProductDetailRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<WarehouseProductDetailRecord, Integer> getIdentity() {
        return Keys.IDENTITY_WAREHOUSE_PRODUCT_DETAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<WarehouseProductDetailRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<WarehouseProductDetailRecord>> getKeys() {
        return Arrays.<UniqueKey<WarehouseProductDetailRecord>>asList(Keys.CONSTRAINT_2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WarehouseProductDetail as(String alias) {
        return new WarehouseProductDetail(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public WarehouseProductDetail rename(String name) {
        return new WarehouseProductDetail(name, null);
    }
}