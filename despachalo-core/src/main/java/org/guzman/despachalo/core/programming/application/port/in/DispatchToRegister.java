package org.guzman.despachalo.core.programming.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class DispatchToRegister {
    @NotNull
    private LocalDateTime departureTime;

    @NotEmpty
    private List<Long> orderIds;
}
