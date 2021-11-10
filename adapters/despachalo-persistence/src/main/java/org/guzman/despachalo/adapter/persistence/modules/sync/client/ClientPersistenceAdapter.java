package org.guzman.despachalo.adapter.persistence.modules.sync.client;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterClientsPort;
import org.guzman.despachalo.core.sync.load.domain.ClientToRegister;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ClientPersistenceAdapter implements RegisterClientsPort {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public void registerClients(List<ClientToRegister> toRegister) {
        var now = LocalDateTime.now();
        var codes = toRegister.stream()
                .map(ClientToRegister::getCode)
                .collect(Collectors.toList());

        var toUpdate = clientRepository.findAllByCodeIn(codes)
                .stream()
                .collect(Collectors.toMap(ClientEntity::getCode, Function.identity()));;

        var rows = toRegister.stream()
                .map(client -> {
                    if (toUpdate.containsKey(client.getCode())) {
                        var row = toUpdate.get(client.getCode());
                        row.setRuc(client.getRuc());
                        row.setBusinessName(client.getBusinessName());
                        row.setUpdatedAt(now);
                        return row;
                    }

                    var row = clientMapper.toEntity(client);
                    row.setCreatedAt(now);
                    return row;
                })
                .collect(Collectors.toList());

        clientRepository.saveAll(rows);
    }
}
