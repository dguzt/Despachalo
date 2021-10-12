package org.guzman.despachalo.web.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.programming.application.port.in.GetDispatchDetailsUseCase;
import org.guzman.despachalo.core.programming.application.port.out.GetDispatchAreasPort;
import org.guzman.despachalo.core.programming.domain.AreaWithOrders;
import org.guzman.despachalo.core.programming.domain.DispatchDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetDispatchDetailsController {
    private final GetDispatchDetailsUseCase useCase;
    private final GetDispatchAreasPort getDispatchAreasPort;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/programming/dispatches/{dispatchId}")
    public DispatchDetails getDispatchDetails(@PathVariable("dispatchId") Long dispatchId) {
        return useCase.execute(dispatchId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/programming/dispatches/{dispatchId}/areas")
    public List<AreaWithOrders> getDispatchAreas(@PathVariable("dispatchId") Long dispatchId) {
        return getDispatchAreasPort.getDispatchAreas(dispatchId);
    }
}
