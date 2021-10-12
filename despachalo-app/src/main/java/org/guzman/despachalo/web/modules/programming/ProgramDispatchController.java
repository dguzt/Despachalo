package org.guzman.despachalo.web.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.programming.application.port.in.DispatchToRegister;
import org.guzman.despachalo.core.programming.application.port.in.ProgramDispatchUseCase;
import org.guzman.despachalo.core.programming.domain.Dispatch;
import org.guzman.despachalo.web.config.security.model.WebUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ProgramDispatchController {
    private final ProgramDispatchUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/programming/dispatches")
    public Dispatch programDispatch(@RequestBody DispatchToRegister toRegister,
                                    @AuthenticationPrincipal WebUserDetails userDetails) {
        toRegister.setDepartureTime(toRegister.getDepartureTime().minusHours(5L));
        var analystId = userDetails.getUserId();
        return useCase.execute(toRegister, analystId);
    }
}
