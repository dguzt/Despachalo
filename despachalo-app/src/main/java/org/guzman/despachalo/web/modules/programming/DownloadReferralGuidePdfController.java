package org.guzman.despachalo.web.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.programming.application.port.in.GetDispatchReferralGuideUseCase;
import org.guzman.despachalo.external.awss3.AwsStorageExternalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class DownloadReferralGuidePdfController {
    private static final String LOADS_PATH = "guides/";
    private final AwsStorageExternalService awsStorageExternalService;
    private final GetDispatchReferralGuideUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/programming/dispatches/{dispatchId}/vehicles/{vehicleId}/orders/{orderId}/guide")
    public String getDispatchDetails(
            @PathVariable("dispatchId") Long dispatchId,
            @PathVariable("vehicleId") Long programmedVehicleId,
            @PathVariable("orderId") Long orderId) throws IOException {

        var file = useCase.execute(dispatchId, programmedVehicleId, orderId);
        var fileKey = awsStorageExternalService.saveFile(file, LOADS_PATH);
        return awsStorageExternalService.getUrlFile(fileKey);
    }
}
