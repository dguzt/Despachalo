package org.guzman.despachalo.core.storage.application.port.out;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.domain.Shipment;

public interface GetPaginatedShipmentsPort {
    Paginator<Shipment> getPage(Filters filters);
}
