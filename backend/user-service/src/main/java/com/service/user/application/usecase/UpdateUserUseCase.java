package com.service.user.application.usecase;

import com.service.user.domain.model.User;
import com.service.user.domain.ports.UserRepositoryPort;

import java.util.UUID;

public class UpdateUserUseCase {

    private final UserRepositoryPort userRepository;

    public UpdateUserUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UUID userId, String name) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(name);

        return userRepository.save(user);
    }
}