package com.service.user.application.usecase;

import com.service.user.domain.model.User;
import com.service.user.domain.model.UserStatus;
import com.service.user.domain.ports.UserRepositoryPort;

import java.util.UUID;

public class GetCurrentUserUseCase {

    private final UserRepositoryPort userRepository;

    public GetCurrentUserUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String keycloakId, String email, String name) {

        return userRepository.findByKeycloakId(keycloakId)
                .orElseGet(() -> {
                    User user = new User();
                    user.setId(UUID.randomUUID());
                    user.setKeycloakId(keycloakId);
                    user.setEmail(email);
                    user.setName(name);
                    user.setStatus(UserStatus.ACTIVE);
                    return userRepository.save(user);
                });
    }
}