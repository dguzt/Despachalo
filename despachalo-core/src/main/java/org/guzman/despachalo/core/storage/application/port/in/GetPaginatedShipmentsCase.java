package org.guzman.despachalo.core.storage.application.port.in;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.domain.Role;
import org.guzman.despachalo.core.storage.domain.Shipment;

public interface GetPaginatedShipmentsCase {
    Paginator<Shipment> execute(Filters filters);
}
