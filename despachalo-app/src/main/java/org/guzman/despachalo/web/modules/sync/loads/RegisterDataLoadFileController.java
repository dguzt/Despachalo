package org.guzman.despachalo.web.modules.sync.loads;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.sync.load.application.port.in.RegisterDataLoadFileUseCase;
import org.guzman.despachalo.web.config.security.model.WebUserDetails;
import org.guzman.despachalo.web.helpers.TmpFileHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterDataLoadFileController {
    private final TmpFileHelper tmpFileHelper;
    private final RegisterDataLoadFileUseCase useCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/sync/loads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void registerSpecialistsBulkLoad(@RequestParam("file") MultipartFile csvMultipartFile,
                                            @AuthenticationPrincipal WebUserDetails userDetails) throws IOException {
        var originalName = csvMultipartFile.getOriginalFilename();
        var csvFile = tmpFileHelper.multipartToTmpFile(csvMultipartFile);
        useCase.execute(userDetails.getUserId(), csvFile, originalName);
    }
}
