package org.guzman.despachalo.core.user.application.port.in;

import org.guzman.despachalo.core.user.domain.User;

public interface GetUserUseCase {
    User execute(String email);
    User execute(Long id);
}
