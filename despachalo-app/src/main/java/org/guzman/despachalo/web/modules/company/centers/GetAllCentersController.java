package org.guzman.despachalo.web.modules.company.centers;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.company.application.port.in.CenterData;
import org.guzman.despachalo.core.company.application.port.in.GetAllCentersDataUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetAllCentersController {
    private final GetAllCentersDataUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/company/centers/all")
    public List<CenterData> getAllCenters() {
        return useCase.execute();
    }
}
