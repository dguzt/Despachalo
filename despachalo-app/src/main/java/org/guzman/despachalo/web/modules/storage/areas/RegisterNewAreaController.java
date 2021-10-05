package org.guzman.despachalo.web.modules.storage.areas;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.storage.application.port.in.AreaToRegister;
import org.guzman.despachalo.core.storage.application.port.in.RegisterNewAreaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterNewAreaController {
    private final RegisterNewAreaUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/storage/areas")
    public Long registerArea(@RequestBody AreaToRegister toRegister) {
        return useCase.execute(toRegister);
    }
}
