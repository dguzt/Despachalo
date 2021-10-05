package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.company.application.port.out.CheckIfCenterExistsPort;
import org.guzman.despachalo.core.storage.application.port.in.RegisterNewAreaUseCase;
import org.guzman.despachalo.core.storage.application.port.in.AreaToRegister;
import org.guzman.despachalo.core.storage.application.port.out.RegisterAreaPort;

@UseCase
@RequiredArgsConstructor
public class RegisterNewAreaService implements RegisterNewAreaUseCase {
    private final CheckIfCenterExistsPort centerPort;
    private final RegisterAreaPort areaPort;

    @Override
    public Long execute(AreaToRegister toRegister) {
        if (!centerPort.checkIfCenterExists(toRegister.getCenterId())) {
            throw new UnknownCenterForAreaException(toRegister.getCenterId());
        }

        return areaPort.registerArea(toRegister);
    }
}
