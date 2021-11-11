package org.guzman.despachalo.adapter.persistence.modules.sync.client;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.sync.load.application.port.out.GetMappedDestinationPointIdsPort;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterDestinationPointsPort;
import org.guzman.despachalo.core.sync.load.domain.DestinationPointToRegister;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class EndPointPersistenceAdapter implements RegisterDestinationPointsPort, GetMappedDestinationPointIdsPort {
    private final EndPointMapper mapper;
    private final EndPointRepository endPointRepository;

    @Override
    public void registerDestinationPoints(List<DestinationPointToRegister> toRegister) {
        var now = LocalDateTime.now();
        var codes = toRegister.stream()
                .map(DestinationPointToRegister::getCode)
                .collect(Collectors.toList());

        var toUpdate = endPointRepository.findAllByCodeIn(codes)
                .stream()
                .collect(Collectors.toMap(EndPointEntity::getCode, Function.identity()));;

        var rows = toRegister.stream()
                .map(mapper::toEntity)
                .map(endPoint -> {
                    if (toUpdate.containsKey(endPoint.getCode())) {
                        var row = toUpdate.get(endPoint.getCode());
                        endPoint.setId(row.getId());
                        return endPoint;
                    }

                    return endPoint;
                })
                .collect(Collectors.toList());

        endPointRepository.saveAll(rows);
    }

    @Override
    public Map<String, Long> getMappedDestinationPointIds(List<String> codes) {
        return endPointRepository.findAllByCodeIn(codes)
                .stream()
                .collect(Collectors.toMap(
                        EndPointEntity::getCode,
                        EndPointEntity::getId));
    }
}
