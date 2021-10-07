package org.guzman.despachalo.web.modules.storage.shipments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter @Getter
@NoArgsConstructor
public class ConfirmInfo {
    private Long areaId;
    private Long centerId;
    private List<Long> orderIds;
}
