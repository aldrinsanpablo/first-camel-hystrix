/*
 * This file is generated by jOOQ.
*/
package com.myStore.ProductManagement.model;


import com.myStore.ProductManagement.model.tables.Category;
import com.myStore.ProductManagement.model.tables.Product;
import com.myStore.ProductManagement.model.tables.ProductCategory;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in PRDMGT
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>PRDMGT.CATEGORY</code>.
     */
    public static final Category CATEGORY = com.myStore.ProductManagement.model.tables.Category.CATEGORY;

    /**
     * The table <code>PRDMGT.PRODUCT</code>.
     */
    public static final Product PRODUCT = com.myStore.ProductManagement.model.tables.Product.PRODUCT;

    /**
     * The table <code>PRDMGT.PRODUCT_CATEGORY</code>.
     */
    public static final ProductCategory PRODUCT_CATEGORY = com.myStore.ProductManagement.model.tables.ProductCategory.PRODUCT_CATEGORY;
}
