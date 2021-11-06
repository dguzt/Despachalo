package org.guzman.despachalo.web.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.programming.application.port.in.GetDispatchReferralGuideUseCase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class DownloadReferralGuidePdfController {
    private final GetDispatchReferralGuideUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/programming/dispatches/{dispatchId}/vehicles/{vehicleId}/orders/{orderId}/guide")
    public ResponseEntity<Object> getDispatchDetails(
            @PathVariable("dispatchId") Long dispatchId,
            @PathVariable("vehicleId") Long programmedVehicleId,
            @PathVariable("orderId") Long orderId) throws IOException {

        final var filePath = useCase.execute(dispatchId, programmedVehicleId, orderId).getAbsolutePath();
        final var pdfBytes = Files.readAllBytes(Paths.get(filePath));

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData("attachment", null);
        headers.setCacheControl("no-cache");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
