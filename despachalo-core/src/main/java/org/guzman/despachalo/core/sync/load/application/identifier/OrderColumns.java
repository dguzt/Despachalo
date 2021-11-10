package org.guzman.despachalo.core.sync.load.application.identifier;

import java.util.List;

public final class OrderColumns {
    public static final Integer ORDER_CODE             = 0;
    public static final Integer DESTINATION_POINT_CODE = 1;
    public static final Integer PRODUCT_CODE           = 2;
    public static final Integer REQUESTED_AMOUNT       = 3;

    public static final String NAME_COL_ORDER_CODE             = "codigo pedido";
    public static final String NAME_COL_DESTINATION_POINT_CODE = "codigo punto destino";
    public static final String NAME_COL_PRODUCT_CODE           = "codigo producto*";
    public static final String NAME_COL_REQUESTED_AMOUNT       = "cantidad solicitada*";

    public static final List<Object> NAME_COLUMNS = List.of(
            NAME_COL_ORDER_CODE,
            NAME_COL_DESTINATION_POINT_CODE,
            NAME_COL_PRODUCT_CODE,
            NAME_COL_REQUESTED_AMOUNT
    );

    public static final List<Object> NAME_COLUMNS_STATIC = List.of(
            NAME_COL_ORDER_CODE,
            NAME_COL_DESTINATION_POINT_CODE
    );

    public static final List<Object> NAME_COLUMNS_VAR = List.of(
            NAME_COL_PRODUCT_CODE,
            NAME_COL_REQUESTED_AMOUNT
    );
}
