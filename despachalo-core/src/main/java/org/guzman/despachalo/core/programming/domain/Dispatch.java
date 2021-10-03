package org.guzman.despachalo.core.programming.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Dispatch {
    private Long id;
    private LocalDateTime departureTime;
    private String state;
    private Long analystId;
}
