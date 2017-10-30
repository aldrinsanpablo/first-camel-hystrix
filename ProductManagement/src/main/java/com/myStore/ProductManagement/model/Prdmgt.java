/*
 * This file is generated by jOOQ.
*/
package com.myStore.ProductManagement.model;


import com.myStore.ProductManagement.model.tables.Category;
import com.myStore.ProductManagement.model.tables.Product;
import com.myStore.ProductManagement.model.tables.ProductCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Prdmgt extends SchemaImpl {

    private static final long serialVersionUID = 85159652;

    /**
     * The reference instance of <code>PRDMGT</code>
     */
    public static final Prdmgt PRDMGT = new Prdmgt();

    /**
     * The table <code>PRDMGT.CATEGORY</code>.
     */
    public final Category CATEGORY = com.myStore.ProductManagement.model.tables.Category.CATEGORY;

    /**
     * The table <code>PRDMGT.PRODUCT</code>.
     */
    public final Product PRODUCT = com.myStore.ProductManagement.model.tables.Product.PRODUCT;

    /**
     * The table <code>PRDMGT.PRODUCT_CATEGORY</code>.
     */
    public final ProductCategory PRODUCT_CATEGORY = com.myStore.ProductManagement.model.tables.ProductCategory.PRODUCT_CATEGORY;

    /**
     * No further instances allowed
     */
    private Prdmgt() {
        super("PRDMGT", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.SYSTEM_SEQUENCE_1BF1CBDF_D751_4D48_9F53_41AB288CA452,
            Sequences.SYSTEM_SEQUENCE_770DE9A9_04EE_49C2_A199_C16AA62F751E,
            Sequences.SYSTEM_SEQUENCE_CDD00ABF_C30B_489B_ACE4_0716F781FEBA);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Category.CATEGORY,
            Product.PRODUCT,
            ProductCategory.PRODUCT_CATEGORY);
    }
}