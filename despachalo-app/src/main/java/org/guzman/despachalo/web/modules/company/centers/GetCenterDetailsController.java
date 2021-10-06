package org.guzman.despachalo.web.modules.company.centers;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.company.application.port.in.GetCenterDetailsUseCase;
import org.guzman.despachalo.core.company.domain.DistributionCenter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetCenterDetailsController {
    private final GetCenterDetailsUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/company/centers/{centerId}")
    public DistributionCenter getShipments(@PathVariable("centerId") Long centerId) {
        return useCase.execute(centerId);
    }
}
