package org.guzman.despachalo.core.programming.application.port.out;

import java.util.List;
import java.util.Set;

public interface RegisterRoutesPort {
    void registerRoutes(Set<List<RouteToRegister>> toRegister);
}
