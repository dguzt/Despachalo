package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.modules.storage.commodity.ItemMapper;
import org.guzman.despachalo.adapter.persistence.modules.storage.commodity.ItemRepository;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.storage.application.port.out.AreOrderItemsStoredPort;
import org.guzman.despachalo.core.storage.application.port.out.GetItemsStoredInAreaPort;
import org.guzman.despachalo.core.storage.application.port.out.StoreOrderItemsInAreaPort;
import org.guzman.despachalo.core.storage.domain.Item;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class StoredItemPersistenceAdapter implements
        GetItemsStoredInAreaPort,
        AreOrderItemsStoredPort,
        StoreOrderItemsInAreaPort {

    private final AreaRepository areaRepository;
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

    @Override
    public void storeOrderItemsInArea(Long areaId, Long orderId, Long commodityId) {
        var items = itemRepository.findAllByCommodityIdAndOrderId(commodityId, orderId);
        var area = areaRepository.findById(areaId)
                .orElseThrow(() -> {
                    var message = String.format(
                            "Area to store order items not found (area: %d, order: %d, shipment: %d)",
                            areaId, orderId, commodityId);
                    return new RuntimeException(message);
                });

        var rows = items.stream()
                .map(item -> {
                    var stored = new StoredItemEntity();
                    stored.setAreaId(areaId);
                    stored.setItemId(item.getId());
                    stored.setStoredAt(LocalDateTime.now());
                    return stored;
                })
                .collect(Collectors.toList());
        storeItemRepository.saveAll(rows);

        area.setAvailableCapacity(area.getAvailableCapacity() - rows.size());
        areaRepository.save(area);
    }
}
