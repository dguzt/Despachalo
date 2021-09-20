package org.guzman.despachalo.adapter.persistence.user;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.user.application.port.out.GetUserPort;
import org.guzman.despachalo.core.user.domain.User;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class OldUserPersistenceAdapter implements GetUserPort {
    private final OldUserRepository repository;
    private final OldUserMapper mapper;

    @Override
    public Optional<User> getById(Long id) {
        return repository
                .findById(id)
                .map(mapper::toUser);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return repository
                .findByEmail(email)
                .map(mapper::toUser);
    }
}
