package org.guzman.despachalo.core.sync.load.application.identifier;

import java.util.List;

public final class CommodityColumns {
    public static final Integer COMMODITY_CODE    = 0;
    public static final Integer ORIGIN_POINT_CODE = 1;
    public static final Integer PLATE             = 2;
    public static final Integer DELIVERY_GEOCODE  = 3;
    public static final Integer ARRIVAL_DATE      = 4;
    public static final Integer ITEM_CODE         = 5;
    public static final Integer ITEM_PRODUCT_CODE = 6;
    public static final Integer ITEM_ORDER_CODE   = 7;

    public static final String NAME_COL_COMMODITY_CODE    = "codigo mercaderia";
    public static final String NAME_COL_ORIGIN_POINT_CODE = "codigo punto origen";
    public static final String NAME_COL_PLATE             = "placa vehiculo";
    public static final String NAME_COL_DELIVERY_GEOCODE  = "ubigeo entrega";
    public static final String NAME_COL_ARRIVAL_DATE      = "fecha llegada";
    public static final String NAME_COL_ITEM_CODE         = "codigo item*";
    public static final String NAME_COL_ITEM_PRODUCT_CODE = "codigo producto*";
    public static final String NAME_COL_ITEM_ORDER_CODE   = "codigo pedido*";

    public static final List<Object> NAME_COLUMNS = List.of(
            NAME_COL_COMMODITY_CODE,
            NAME_COL_ORIGIN_POINT_CODE,
            NAME_COL_PLATE,
            NAME_COL_DELIVERY_GEOCODE,
            NAME_COL_ARRIVAL_DATE,
            NAME_COL_ITEM_CODE,
            NAME_COL_ITEM_PRODUCT_CODE,
            NAME_COL_ITEM_ORDER_CODE
    );

    public static final List<Object> NAME_COLUMNS_STATIC = List.of(
            NAME_COL_COMMODITY_CODE,
            NAME_COL_ORIGIN_POINT_CODE,
            NAME_COL_PLATE,
            NAME_COL_DELIVERY_GEOCODE,
            NAME_COL_ARRIVAL_DATE
    );

    public static final List<Object> NAME_COLUMNS_VAR = List.of(
            NAME_COL_ITEM_CODE,
            NAME_COL_ITEM_PRODUCT_CODE,
            NAME_COL_ITEM_ORDER_CODE
    );
}
