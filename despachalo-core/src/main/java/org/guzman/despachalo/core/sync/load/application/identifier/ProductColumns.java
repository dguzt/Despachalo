package org.guzman.despachalo.core.sync.load.application.identifier;

import java.util.List;

public final class ProductColumns {
    public static final Integer PRODUCT_CODE = 0;
    public static final Integer DESCRIPTION  = 1;
    public static final Integer WEIGHT       = 2;
    public static final Integer VOLUME       = 3;

    public static final String NAME_COL_PRODUCT_CODE = "codigo producto";
    public static final String NAME_COL_DESCRIPTION  = "descripcion";
    public static final String NAME_COL_WEIGHT       = "peso";
    public static final String NAME_COL_VOLUME       = "volumen";

    public static final List<Object> NAME_COLUMNS = List.of(
      NAME_COL_PRODUCT_CODE,
      NAME_COL_DESCRIPTION,
      NAME_COL_WEIGHT,
      NAME_COL_VOLUME
    );
}
