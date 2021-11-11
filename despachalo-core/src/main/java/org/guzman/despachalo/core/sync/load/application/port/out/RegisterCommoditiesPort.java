package org.guzman.despachalo.core.sync.load.application.port.out;

import org.guzman.despachalo.core.sync.load.domain.CommodityToRegister;

import java.util.List;

public interface RegisterCommoditiesPort {
    void registerCommodities(List<CommodityToRegister> toRegister);
}
