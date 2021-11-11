package org.guzman.despachalo.adapter.persistence.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.config.MappingDistanceVars;
import org.guzman.despachalo.adapter.persistence.modules.route.DistanceEntity;
import org.guzman.despachalo.adapter.persistence.modules.route.DistanceRepository;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class MappingDistancePersistenceAdapter {
    private final MappingDistanceVars mappingDistanceVars;
    private final DistanceRepository distanceRepository;

    public void request() {
        var centerRows = List.of(
                new DistanceEntity(null, 1L, null, 1L, 5989d, null, null, null),
                new DistanceEntity(null, 2L, null, 1L, 6300d, null, null, null),
                new DistanceEntity(null, 3L, null, 1L, 5388d, null, null, null),
                new DistanceEntity(null, 4L, null, 1L, 5866d, null, null, null),
                new DistanceEntity(null, 5L, null, 1L, 5746d, null, null, null),
                new DistanceEntity(null, 6L, null, 1L, 4758d, null, null, null),
                new DistanceEntity(null, 7L, null, 1L, 3980d, null, null, null),
                new DistanceEntity(null, 8L, null, 1L, 1635d, null, null, null),
                new DistanceEntity(null, 9L, null, 1L, 1064d, null, null, null),
                new DistanceEntity(null, null, 1L, 1L, 5989d, null, null, null),
                new DistanceEntity(null, null, 2L, 1L, 6300d, null, null, null),
                new DistanceEntity(null, null, 3L, 1L, 5388d, null, null, null),
                new DistanceEntity(null, null, 4L, 1L, 5866d, null, null, null),
                new DistanceEntity(null, null, 5L, 1L, 5746d, null, null, null),
                new DistanceEntity(null, null, 6L, 1L, 4758d, null, null, null),
                new DistanceEntity(null, null, 7L, 1L, 3980d, null, null, null),
                new DistanceEntity(null, null, 8L, 1L, 1635d, null, null, null),
                new DistanceEntity(null, null, 9L, 1L, 1064d, null, null, null)
        );

        var client01 = List.of(
                new DistanceEntity(null, 2L, 1L, null, 2247d, null, null, null),
                new DistanceEntity(null, 3L, 1L, null, 7595d, null, null, null),
                new DistanceEntity(null, 4L, 1L, null, 8579d, null, null, null),
                new DistanceEntity(null, 5L, 1L, null, 8890d, null, null, null),
                new DistanceEntity(null, 6L, 1L, null, 8396d, null, null, null),
                new DistanceEntity(null, 7L, 1L, null, 7088d, null, null, null),
                new DistanceEntity(null, 8L, 1L, null, 7441d, null, null, null),
                new DistanceEntity(null, 9L, 1L, null, 6916d, null, null, null),
                new DistanceEntity(null, 1L, 2L, null, 2247d, null, null, null),
                new DistanceEntity(null, 1L, 3L, null, 7595d, null, null, null),
                new DistanceEntity(null, 1L, 4L, null, 8579d, null, null, null),
                new DistanceEntity(null, 1L, 5L, null, 8890d, null, null, null),
                new DistanceEntity(null, 1L, 6L, null, 8396d, null, null, null),
                new DistanceEntity(null, 1L, 7L, null, 7088d, null, null, null),
                new DistanceEntity(null, 1L, 8L, null, 7441d, null, null, null),
                new DistanceEntity(null, 1L, 9L, null, 6916d, null, null, null)
        );

        var client02 = List.of(
                new DistanceEntity(null, 3L, 2L, null, 7233d, null, null, null),
                new DistanceEntity(null, 4L, 2L, null, 8217d, null, null, null),
                new DistanceEntity(null, 5L, 2L, null, 8528d, null, null, null),
                new DistanceEntity(null, 6L, 2L, null, 8033d, null, null, null),
                new DistanceEntity(null, 7L, 2L, null, 7995d, null, null, null),
                new DistanceEntity(null, 8L, 2L, null, 7751d, null, null, null),
                new DistanceEntity(null, 9L, 2L, null, 7226d, null, null, null),
                new DistanceEntity(null, 2L, 3L, null, 7233d, null, null, null),
                new DistanceEntity(null, 2L, 4L, null, 8217d, null, null, null),
                new DistanceEntity(null, 2L, 5L, null, 8528d, null, null, null),
                new DistanceEntity(null, 2L, 6L, null, 8033d, null, null, null),
                new DistanceEntity(null, 2L, 7L, null, 7995d, null, null, null),
                new DistanceEntity(null, 2L, 8L, null, 7751d, null, null, null),
                new DistanceEntity(null, 2L, 9L, null, 7226d, null, null, null)
        );

        var client03 = List.of(
                new DistanceEntity(null, 4L, 3L, null, 1371d, null, null, null),
                new DistanceEntity(null, 5L, 3L, null, 1334d, null, null, null),
                new DistanceEntity(null, 6L, 3L, null, 3696d, null, null, null),
                new DistanceEntity(null, 7L, 3L, null, 5183d, null, null, null),
                new DistanceEntity(null, 8L, 3L, null, 7756d, null, null, null),
                new DistanceEntity(null, 9L, 3L, null, 7599d, null, null, null),
                new DistanceEntity(null, 3L, 4L, null, 1371d, null, null, null),
                new DistanceEntity(null, 3L, 5L, null, 1334d, null, null, null),
                new DistanceEntity(null, 3L, 6L, null, 3696d, null, null, null),
                new DistanceEntity(null, 3L, 7L, null, 5183d, null, null, null),
                new DistanceEntity(null, 3L, 8L, null, 7756d, null, null, null),
                new DistanceEntity(null, 3L, 9L, null, 7599d, null, null, null)
        );

        var client04 = List.of(
                new DistanceEntity(null, 5L, 4L, null, 767d, null, null, null),
                new DistanceEntity(null, 6L, 4L, null, 2759d, null, null, null),
                new DistanceEntity(null, 7L, 4L, null, 4246d, null, null, null),
                new DistanceEntity(null, 8L, 4L, null, 6819d, null, null, null),
                new DistanceEntity(null, 9L, 4L, null, 6662d, null, null, null),
                new DistanceEntity(null, 4L, 5L, null, 767d, null, null, null),
                new DistanceEntity(null, 4L, 6L, null, 2759d, null, null, null),
                new DistanceEntity(null, 4L, 7L, null, 4246d, null, null, null),
                new DistanceEntity(null, 4L, 8L, null, 6819d, null, null, null),
                new DistanceEntity(null, 4L, 9L, null, 6662d, null, null, null)
        );

        var client05 = List.of(
                new DistanceEntity(null, 6L, 5L, null, 2506d, null, null, null),
                new DistanceEntity(null, 7L, 5L, null, 3993d, null, null, null),
                new DistanceEntity(null, 8L, 5L, null, 6566d, null, null, null),
                new DistanceEntity(null, 9L, 5L, null, 6410d, null, null, null),
                new DistanceEntity(null, 5L, 6L, null, 2506d, null, null, null),
                new DistanceEntity(null, 5L, 7L, null, 3993d, null, null, null),
                new DistanceEntity(null, 5L, 8L, null, 6566d, null, null, null),
                new DistanceEntity(null, 5L, 9L, null, 6410d, null, null, null)
        );

        var client06 = List.of(
                new DistanceEntity(null, 7L, 6L, null, 1118d, null, null, null),
                new DistanceEntity(null, 8L, 6L, null, 5558d, null, null, null),
                new DistanceEntity(null, 9L, 6L, null, 5401d, null, null, null),
                new DistanceEntity(null, 6L, 7L, null, 1118d, null, null, null),
                new DistanceEntity(null, 6L, 8L, null, 5558d, null, null, null),
                new DistanceEntity(null, 6L, 9L, null, 5401d, null, null, null)
        );

        var client07 = List.of(
                new DistanceEntity(null, 8L, 7L, null, 4719d, null, null, null),
                new DistanceEntity(null, 9L, 7L, null, 4562d, null, null, null),
                new DistanceEntity(null, 7L, 8L, null, 4719d, null, null, null),
                new DistanceEntity(null, 7L, 9L, null, 4562d, null, null, null)
        );

        var client08 = List.of(
                new DistanceEntity(null, 9L, 8L, null, 1322d, null, null, null),
                new DistanceEntity(null, 8L, 9L, null, 1322d, null, null, null)
        );

        distanceRepository.saveAll(centerRows);
        distanceRepository.saveAll(client01);
        distanceRepository.saveAll(client02);
        distanceRepository.saveAll(client03);
        distanceRepository.saveAll(client04);
        distanceRepository.saveAll(client05);
        distanceRepository.saveAll(client06);
        distanceRepository.saveAll(client07);
        distanceRepository.saveAll(client08);
    }
}
