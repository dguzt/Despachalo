package org.guzman.despachalo.core.programming.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.core.common.domain.IdentityDoc;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalVehicleData {
    private String plate;
    private IdentityDoc driver;
}
