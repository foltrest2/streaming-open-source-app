package com.service.user.application.usecase;

import com.service.user.domain.model.User;
import com.service.user.domain.ports.UserRepositoryPort;

public class GetUserByKeycloakIdUseCase {

    private final UserRepositoryPort userRepository;

    public GetUserByKeycloakIdUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String keycloakId) {
        return userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}