package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterOriginPointsPort;
import org.guzman.despachalo.core.sync.load.domain.OriginPointToRegister;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class OriginPointPersistenceAdapter implements RegisterOriginPointsPort {
    private final OriginPointRepository originPointRepository;
    private final OriginPointMapper originPointMapper;

    @Override
    public void registerOriginPoints(List<OriginPointToRegister> toRegister) {
        var now = LocalDateTime.now();
        var codes = toRegister.stream()
                .map(OriginPointToRegister::getCode)
                .collect(Collectors.toList());

        var toUpdate = originPointRepository.findAllByCodeIn(codes)
                .stream()
                .collect(Collectors.toMap(OriginPointEntity::getCode, Function.identity()));
        ;

        var rows = toRegister.stream()
                .map(originPoint -> {
                    if (toUpdate.containsKey(originPoint.getCode())) {
                        var row = toUpdate.get(originPoint.getCode());
                        row.setAddress(originPoint.getAddress());
                        row.setUpdatedAt(now);
                        return row;
                    }

                    var row = originPointMapper.toEntity(originPoint);
                    row.setCreatedAt(now);
                    return row;
                })
                .collect(Collectors.toList());

        originPointRepository.saveAll(rows);
    }
}
