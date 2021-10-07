package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.modules.storage.commodity.ItemMapper;
import org.guzman.despachalo.adapter.persistence.modules.storage.commodity.ItemRepository;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.storage.application.port.out.AreOrderItemsStoredPort;
import org.guzman.despachalo.core.storage.application.port.out.GetItemsStoredInAreaPort;
import org.guzman.despachalo.core.storage.domain.Item;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class StoredItemPersistenceAdapter implements
        GetItemsStoredInAreaPort,
        AreOrderItemsStoredPort {
    private final ItemRepository itemRepository;
    private final StoreItemRepository storeItemRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<Item> getItemsStoredInArea(Long areaId) {
        return storeItemRepository.findAllByAreaId(areaId)
                .stream()
                .map(StoredItemEntity::getItem)
                .map(itemMapper::toItem)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean areOrderItemsStored(Long orderId, Long commodityId) {
        var total = itemRepository.countAllByCommodityIdAndOrderId(commodityId, orderId);
        var stored = storeItemRepository.countAllByItem_CommodityIdAndItem_OrderId(commodityId, orderId);
        return Objects.equals(stored, total);
    }
}
