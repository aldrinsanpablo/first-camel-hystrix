/*
 * This file is generated by jOOQ.
*/
package com.myStore.ProductManagement.model;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in PRDMGT
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>PRDMGT.SYSTEM_SEQUENCE_1BF1CBDF_D751_4D48_9F53_41AB288CA452</code>
     */
    public static final Sequence<Long> SYSTEM_SEQUENCE_1BF1CBDF_D751_4D48_9F53_41AB288CA452 = new SequenceImpl<Long>("SYSTEM_SEQUENCE_1BF1CBDF_D751_4D48_9F53_41AB288CA452", Prdmgt.PRDMGT, org.jooq.impl.SQLDataType.BIGINT);

    /**
     * The sequence <code>PRDMGT.SYSTEM_SEQUENCE_770DE9A9_04EE_49C2_A199_C16AA62F751E</code>
     */
    public static final Sequence<Long> SYSTEM_SEQUENCE_770DE9A9_04EE_49C2_A199_C16AA62F751E = new SequenceImpl<Long>("SYSTEM_SEQUENCE_770DE9A9_04EE_49C2_A199_C16AA62F751E", Prdmgt.PRDMGT, org.jooq.impl.SQLDataType.BIGINT);

    /**
     * The sequence <code>PRDMGT.SYSTEM_SEQUENCE_CDD00ABF_C30B_489B_ACE4_0716F781FEBA</code>
     */
    public static final Sequence<Long> SYSTEM_SEQUENCE_CDD00ABF_C30B_489B_ACE4_0716F781FEBA = new SequenceImpl<Long>("SYSTEM_SEQUENCE_CDD00ABF_C30B_489B_ACE4_0716F781FEBA", Prdmgt.PRDMGT, org.jooq.impl.SQLDataType.BIGINT);
}
