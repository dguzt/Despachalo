package org.guzman.despachalo.web.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.programming.application.port.in.ConfirmDispatchUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ConfirmDispatchController {
    private final ConfirmDispatchUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/programming/dispatches/{dispatchId}")
    public void confirmDispatch(@PathVariable Long dispatchId) {
        useCase.execute(dispatchId);
    }
}
