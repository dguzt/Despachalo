package org.guzman.despachalo.core.sync.load.application.identifier;

import java.util.List;

public final class ClientColumns {
    public static final Integer CLIENT_CODE   = 0;
    public static final Integer RUC           = 1;
    public static final Integer BUSINESS_NAME = 2;

    public static final String NAME_COL_CLIENT_CODE   = "codigo cliente";
    public static final String NAME_COL_RUC           = "ruc";
    public static final String NAME_COL_BUSINESS_NAME = "razon social";

    public static final List<Object> NAME_COLUMNS = List.of(
            NAME_COL_CLIENT_CODE,
            NAME_COL_RUC,
            NAME_COL_BUSINESS_NAME
    );
}
