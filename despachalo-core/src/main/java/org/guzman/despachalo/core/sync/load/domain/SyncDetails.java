package org.guzman.despachalo.core.sync.load.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SyncDetails {
    private Load load;
    private String fileUrl;
    private String originalName;
    private Integer okRows;
    private Integer errorRows;
}
