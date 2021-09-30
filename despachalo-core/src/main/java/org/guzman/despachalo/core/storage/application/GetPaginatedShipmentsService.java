package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.application.port.in.GetPaginatedShipmentsCase;
import org.guzman.despachalo.core.storage.application.port.out.GetPaginatedShipmentsPort;
import org.guzman.despachalo.core.storage.domain.Shipment;

@UseCase
@RequiredArgsConstructor
public class GetPaginatedShipmentsService implements GetPaginatedShipmentsCase {
    private final GetPaginatedShipmentsPort getPaginatedShipmentsPort;

    @Override
    public Paginator<Shipment> execute(Filters filters) {
        return getPaginatedShipmentsPort.getPage(filters);
    }
}
