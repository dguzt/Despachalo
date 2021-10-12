package org.guzman.despachalo.web.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.programming.application.port.in.GetDispatchDetailsUseCase;
import org.guzman.despachalo.core.programming.domain.DispatchDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetDispatchDetailsController {
    private final GetDispatchDetailsUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/programming/dispatches/{dispatchId}")
    public DispatchDetails getDispatchDetails(@PathVariable("dispatchId") Long dispatchId) {
        return useCase.execute(dispatchId);
    }
}
