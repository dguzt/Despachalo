package org.guzman.despachalo.web.modules.sync.loads;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.sync.load.application.port.in.GetSyncDetailsUseCase;
import org.guzman.despachalo.core.sync.load.domain.SyncDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetSyncDetailsController {
    private final GetSyncDetailsUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/sync/loads/{syncId}")
    public SyncDetails getSyncDetails(@PathVariable("syncId") Long syncId) {
        return useCase.execute(syncId);
    }
}
