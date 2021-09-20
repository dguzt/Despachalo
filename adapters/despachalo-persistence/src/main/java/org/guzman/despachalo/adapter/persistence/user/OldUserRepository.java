package org.guzman.despachalo.adapter.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OldUserRepository extends JpaRepository<OldUserEntity, Long> {
    Optional<OldUserEntity> findByEmail(String email);
}
