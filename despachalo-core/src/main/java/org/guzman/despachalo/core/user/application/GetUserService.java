package org.guzman.despachalo.core.user.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.user.application.port.in.GetUserUseCase;
import org.guzman.despachalo.core.user.application.port.out.GetUserPort;
import org.guzman.despachalo.core.user.domain.User;

@UseCase
@RequiredArgsConstructor
public class GetUserService implements GetUserUseCase {
    private final GetUserPort getUserPort;

    @Override
    public User execute(String email) {
        return getUserPort.getByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User execute(Long id) {
        return getUserPort.getById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
