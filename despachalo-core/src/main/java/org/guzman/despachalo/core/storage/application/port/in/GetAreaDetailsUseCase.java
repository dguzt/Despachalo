package org.guzman.despachalo.core.storage.application.port.in;

public interface GetAreaDetailsUseCase {
    AreaWithDetails execute(Long areaId);
}
