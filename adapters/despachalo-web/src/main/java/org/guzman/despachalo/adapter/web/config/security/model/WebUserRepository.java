package org.guzman.despachalo.adapter.web.config.security.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebUserRepository extends JpaRepository<WebUserEntity, Long> {
    Optional<WebUserEntity> findByUserEmail(String email);
}
