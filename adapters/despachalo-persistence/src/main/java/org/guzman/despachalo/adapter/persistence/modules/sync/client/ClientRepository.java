package org.guzman.despachalo.adapter.persistence.modules.sync.client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
