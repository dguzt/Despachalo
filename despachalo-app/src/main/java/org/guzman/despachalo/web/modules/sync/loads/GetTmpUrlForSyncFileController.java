package org.guzman.despachalo.web.modules.sync.loads;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.sync.load.application.port.in.GetSyncDetailsUseCase;
import org.guzman.despachalo.external.awss3.AwsStorageExternalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetTmpUrlForSyncFileController {
    private final GetSyncDetailsUseCase useCase;
    private final AwsStorageExternalService storageExternalService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/sync/loads/{syncId}/files")
    public String getTmpUrlForSyncFile(@PathVariable("syncId") Long syncId,
                                       @RequestParam(value = "type", defaultValue = "") String fileType) {
        var sync = useCase.execute(syncId);
        var file = sync.getFileUrl();
        return storageExternalService.getUrlFile(file);
    }
}
