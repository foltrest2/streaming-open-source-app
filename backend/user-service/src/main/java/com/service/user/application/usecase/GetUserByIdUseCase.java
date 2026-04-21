package com.service.user.application.usecase;

import com.service.user.domain.model.User;
import com.service.user.domain.ports.UserRepositoryPort;

import java.util.UUID;

public class GetUserByIdUseCase {

    private final UserRepositoryPort userRepository;

    public GetUserByIdUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}