package org.guzman.despachalo.core.sync.load.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LoadToRegister {
    private Long responsibleId;
    private String state;
    private String dataType;
    private String urlFile;
    private LocalDateTime syncAt;
}
