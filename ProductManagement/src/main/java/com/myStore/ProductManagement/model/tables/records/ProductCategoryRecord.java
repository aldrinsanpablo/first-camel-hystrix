/*
 * This file is generated by jOOQ.
*/
package com.myStore.ProductManagement.model.tables.records;


import com.myStore.ProductManagement.model.tables.ProductCategory;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


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
public class ProductCategoryRecord extends UpdatableRecordImpl<ProductCategoryRecord> implements Record7<Integer, Integer, Integer, Timestamp, String, Timestamp, String> {

    private static final long serialVersionUID = 2043504476;

    /**
     * Setter for <code>PRDMGT.PRODUCT_CATEGORY.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>PRDMGT.PRODUCT_CATEGORY.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>PRDMGT.PRODUCT_CATEGORY.PRODUCT_ID</code>.
     */
    public void setProductId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>PRDMGT.PRODUCT_CATEGORY.PRODUCT_ID</code>.
     */
    public Integer getProductId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>PRDMGT.PRODUCT_CATEGORY.CATEGORY_ID</code>.
     */
    public void setCategoryId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>PRDMGT.PRODUCT_CATEGORY.CATEGORY_ID</code>.
     */
    public Integer getCategoryId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>PRDMGT.PRODUCT_CATEGORY.CREATE_TS</code>.
     */
    public void setCreateTs(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>PRDMGT.PRODUCT_CATEGORY.CREATE_TS</code>.
     */
    public Timestamp getCreateTs() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>PRDMGT.PRODUCT_CATEGORY.CREATE_BY</code>.
     */
    public void setCreateBy(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>PRDMGT.PRODUCT_CATEGORY.CREATE_BY</code>.
     */
    public String getCreateBy() {
        return (String) get(4);
    }

    /**
     * Setter for <code>PRDMGT.PRODUCT_CATEGORY.UPDATE_TS</code>.
     */
    public void setUpdateTs(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>PRDMGT.PRODUCT_CATEGORY.UPDATE_TS</code>.
     */
    public Timestamp getUpdateTs() {
        return (Timestamp) get(5);
    }

    /**
     * Setter for <code>PRDMGT.PRODUCT_CATEGORY.UPDATE_BY</code>.
     */
    public void setUpdateBy(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>PRDMGT.PRODUCT_CATEGORY.UPDATE_BY</code>.
     */
    public String getUpdateBy() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Integer, Integer, Timestamp, String, Timestamp, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Integer, Integer, Timestamp, String, Timestamp, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return ProductCategory.PRODUCT_CATEGORY.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return ProductCategory.PRODUCT_CATEGORY.PRODUCT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return ProductCategory.PRODUCT_CATEGORY.CATEGORY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return ProductCategory.PRODUCT_CATEGORY.CREATE_TS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return ProductCategory.PRODUCT_CATEGORY.CREATE_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return ProductCategory.PRODUCT_CATEGORY.UPDATE_TS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return ProductCategory.PRODUCT_CATEGORY.UPDATE_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getProductId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getCategoryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getCreateTs();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getCreateBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getUpdateTs();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getUpdateBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryRecord value2(Integer value) {
        setProductId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryRecord value3(Integer value) {
        setCategoryId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryRecord value4(Timestamp value) {
        setCreateTs(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryRecord value5(String value) {
        setCreateBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryRecord value6(Timestamp value) {
        setUpdateTs(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryRecord value7(String value) {
        setUpdateBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryRecord values(Integer value1, Integer value2, Integer value3, Timestamp value4, String value5, Timestamp value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProductCategoryRecord
     */
    public ProductCategoryRecord() {
        super(ProductCategory.PRODUCT_CATEGORY);
    }

    /**
     * Create a detached, initialised ProductCategoryRecord
     */
    public ProductCategoryRecord(Integer id, Integer productId, Integer categoryId, Timestamp createTs, String createBy, Timestamp updateTs, String updateBy) {
        super(ProductCategory.PRODUCT_CATEGORY);

        set(0, id);
        set(1, productId);
        set(2, categoryId);
        set(3, createTs);
        set(4, createBy);
        set(5, updateTs);
        set(6, updateBy);
    }
}
