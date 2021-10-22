package org.guzman.despachalo.core.sync.load.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.core.company.domain.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Load {
    private Long id;
    private String state;
    private String dataType;
    private LocalDateTime loadedAt;
    private User responsible;
}
