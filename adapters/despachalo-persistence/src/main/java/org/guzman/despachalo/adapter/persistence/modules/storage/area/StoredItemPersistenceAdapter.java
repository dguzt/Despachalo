package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.modules.storage.commodity.ItemMapper;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.storage.application.port.out.GetItemsStoredInAreaPort;
import org.guzman.despachalo.core.storage.domain.Item;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class StoredItemPersistenceAdapter implements GetItemsStoredInAreaPort {
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
}
