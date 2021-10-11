package org.guzman.despachalo.core.programming.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class DispatchToRegister {
    private LocalDateTime departureTime;
    private Long analystId;
    private List<Long> orderIds;
}
