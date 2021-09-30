package org.guzman.despachalo.web.modules.company.centers;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.company.application.port.in.DistributionCenterToRegister;
import org.guzman.despachalo.core.company.application.port.in.RegisterDistributionCenterUseCase;
import org.guzman.despachalo.core.company.domain.DistributionCenter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterDistributionCenterController {
    private final RegisterDistributionCenterUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/company/centers")
    public DistributionCenter getTruck(@RequestBody DistributionCenterToRegister body) {
        return useCase.execute(body);
    }
}
