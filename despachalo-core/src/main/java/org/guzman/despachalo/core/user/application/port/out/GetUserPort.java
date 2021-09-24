package org.guzman.despachalo.core.user.application.port.out;

import org.guzman.despachalo.core.user.domain.User;

import java.util.Optional;

public interface GetUserPort {
    Optional<User> getById(Long id);
    Optional<User> getByEmail(String email);
}
