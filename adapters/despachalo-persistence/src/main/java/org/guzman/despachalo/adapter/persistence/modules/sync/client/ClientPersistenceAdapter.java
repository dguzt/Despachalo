package org.guzman.despachalo.adapter.persistence.modules.sync.client;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderRepository;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.sync.order.domain.Client;

@PersistenceAdapter
@RequiredArgsConstructor
public class ClientPersistenceAdapter {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final OrderRepository orderRepository;

    public Client getClient(Long clientId) {
        return clientRepository.findById(clientId)
                .map(clientMapper::toClient)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public Client getClientByOrderId(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderRow -> orderRow.getEndPoint().getClient())
                .map(clientMapper::toClient)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        }
}
