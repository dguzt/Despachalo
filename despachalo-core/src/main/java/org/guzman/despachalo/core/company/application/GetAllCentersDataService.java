package org.guzman.despachalo.core.company.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.company.application.port.in.CenterData;
import org.guzman.despachalo.core.company.application.port.in.GetAllCentersDataUseCase;
import org.guzman.despachalo.core.company.application.port.out.GetAllCentersDataPort;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAllCentersDataService implements GetAllCentersDataUseCase {
    private final GetAllCentersDataPort centerPort;

    @Override
    public List<CenterData> execute() {
        return centerPort.getAllCenters();
    }
}
