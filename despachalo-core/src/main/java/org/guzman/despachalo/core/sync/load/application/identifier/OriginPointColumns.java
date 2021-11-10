package org.guzman.despachalo.core.sync.load.application.identifier;

import java.util.List;

public final class OriginPointColumns {
    public static final Integer ORIGIN_POINT_CODE = 0;
    public static final Integer ADDRESS           = 1;

    public static final String NAME_COL_ORIGIN_POINT_CODE = "codigo punto origen";
    public static final String NAME_COL_ADDRESS           = "direccion";

    public static final List<Object> NAME_COLUMNS = List.of(
            NAME_COL_ORIGIN_POINT_CODE,
            NAME_COL_ADDRESS
    );
}
