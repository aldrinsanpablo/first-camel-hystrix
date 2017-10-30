/*
 * This file is generated by jOOQ.
*/
package com.myStore.Warehouse.model.tables.daos;


import com.myStore.Warehouse.model.tables.WarehouseProductDetail;
import com.myStore.Warehouse.model.tables.records.WarehouseProductDetailRecord;

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
public class WarehouseProductDetailDao extends DAOImpl<WarehouseProductDetailRecord, com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail, Integer> {

    /**
     * Create a new WarehouseProductDetailDao without any configuration
     */
    public WarehouseProductDetailDao() {
        super(WarehouseProductDetail.WAREHOUSE_PRODUCT_DETAIL, com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail.class);
    }

    /**
     * Create a new WarehouseProductDetailDao with an attached configuration
     */
    public WarehouseProductDetailDao(Configuration configuration) {
        super(WarehouseProductDetail.WAREHOUSE_PRODUCT_DETAIL, com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>ID IN (values)</code>
     */
    public List<com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail> fetchById(Integer... values) {
        return fetch(WarehouseProductDetail.WAREHOUSE_PRODUCT_DETAIL.ID, values);
    }

    /**
     * Fetch a unique record that has <code>ID = value</code>
     */
    public com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail fetchOneById(Integer value) {
        return fetchOne(WarehouseProductDetail.WAREHOUSE_PRODUCT_DETAIL.ID, value);
    }

    /**
     * Fetch records that have <code>PRODUCT_ID IN (values)</code>
     */
    public List<com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail> fetchByProductId(Integer... values) {
        return fetch(WarehouseProductDetail.WAREHOUSE_PRODUCT_DETAIL.PRODUCT_ID, values);
    }

    /**
     * Fetch records that have <code>AVAILABLE_STOCKS IN (values)</code>
     */
    public List<com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail> fetchByAvailableStocks(Integer... values) {
        return fetch(WarehouseProductDetail.WAREHOUSE_PRODUCT_DETAIL.AVAILABLE_STOCKS, values);
    }

    /**
     * Fetch records that have <code>PRICE IN (values)</code>
     */
    public List<com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail> fetchByPrice(Double... values) {
        return fetch(WarehouseProductDetail.WAREHOUSE_PRODUCT_DETAIL.PRICE, values);
    }
}
