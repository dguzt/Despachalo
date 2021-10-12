package org.guzman.despachalo.core.programming.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.programming.application.port.in.GetDispatchDetailsUseCase;
import org.guzman.despachalo.core.programming.application.port.out.GetDispatchDetailsPort;
import org.guzman.despachalo.core.programming.domain.DispatchDetails;

@UseCase
@RequiredArgsConstructor
public class GetDispatchDetailsService implements GetDispatchDetailsUseCase {
    private final GetDispatchDetailsPort dispatchDetailsPort;

    @Override
    public DispatchDetails execute(Long dispatchId) {
        return dispatchDetailsPort.getDispatchDetails(dispatchId)
                .orElseThrow(() -> new DispatchNotFoundException(dispatchId));
    }
}
