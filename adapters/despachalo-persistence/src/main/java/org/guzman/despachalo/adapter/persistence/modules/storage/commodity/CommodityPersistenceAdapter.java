package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterCommoditiesPort;
import org.guzman.despachalo.core.sync.load.domain.CommodityToRegister;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class CommodityPersistenceAdapter implements RegisterCommoditiesPort {
    private final CommodityRepository commodityRepository;
    private final CommodityMapper commodityMapper;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public void registerCommodities(List<CommodityToRegister> toRegister) {
        var now = LocalDateTime.now();

        var codes = toRegister.stream()
                .map(CommodityToRegister::getCode)
                .collect(Collectors.toList());

        var commodities = commodityRepository.findAllByCodeIn(codes)
                .stream()
                .collect(Collectors.toMap(
                        CommodityEntity::getCode,
                        Function.identity()));

        toRegister.forEach(commoditiesToRegister -> {
            if (commodities.containsKey(commoditiesToRegister.getCode())) {
                return;
            }

            var row = commodityMapper.toEntity(commoditiesToRegister);
            row.setCreatedAt(now);

            var commodityId = commodityRepository.save(row).getId();

            var items = commoditiesToRegister.getItems()
                    .stream()
                    .map(itemMapper::toEntity)
                    .peek(r -> r.setCommodityId(commodityId))
                    .collect(Collectors.toList());

            itemRepository.saveAll(items);
        });
    }
}
