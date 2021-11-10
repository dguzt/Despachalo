package org.guzman.despachalo.adapter.persistence.modules.sync.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    List<ClientEntity> findAllByCodeIn(List<String> codes);
}
