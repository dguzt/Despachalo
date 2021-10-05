package org.guzman.despachalo.core.company.application.port.in;

import java.util.List;

public interface GetAllCentersDataUseCase {
    List<CenterData> execute();
}
