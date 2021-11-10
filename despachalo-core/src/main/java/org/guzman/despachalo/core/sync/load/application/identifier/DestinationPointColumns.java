package org.guzman.despachalo.core.sync.load.application.identifier;

import java.util.List;

public final class DestinationPointColumns {
    public static final Integer DESTINATION_POINT_CODE = 0;
    public static final Integer CLIENT_CODE            = 1;
    public static final Integer ADDRESS                = 2;
    public static final Integer LOCATION_LATITUDE      = 3;
    public static final Integer LOCATION_LONGITUDE     = 4;
    public static final Integer GEOCODE                = 5;

    public static final String NAME_COL_DESTINATION_POINT_CODE = "codigo punto destino";
    public static final String NAME_COL_CLIENT_CODE            = "codigo cliente";
    public static final String NAME_COL_ADDRESS                = "direccion";
    public static final String NAME_COL_LOCATION_LATITUDE      = "ubicacion-latitud";
    public static final String NAME_COL_LOCATION_LONGITUDE     = "ubicacion-longitud";
    public static final String NAME_COL_GEOCODE                = "ubigeo";

    public static final List<Object> NAME_COLUMNS = List.of(
            NAME_COL_DESTINATION_POINT_CODE,
            NAME_COL_CLIENT_CODE,
            NAME_COL_ADDRESS,
            NAME_COL_LOCATION_LATITUDE,
            NAME_COL_LOCATION_LONGITUDE,
            NAME_COL_GEOCODE
    );
}
