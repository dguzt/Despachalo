package org.guzman.despachalo.core.company.application.port.out;

import org.guzman.despachalo.core.company.application.port.in.CenterData;

import java.util.List;

public interface GetAllCentersDataPort {
    List<CenterData> getAllCenters();
}
