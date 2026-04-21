package com.service.user.application.usecase;

import com.service.user.domain.model.User;
import com.service.user.domain.model.UserStatus;
import com.service.user.domain.ports.UserRepositoryPort;

import java.util.UUID;

public class DeleteUserUseCase {

    private final UserRepositoryPort userRepository;

    public DeleteUserUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(UserStatus.DELETED);

        userRepository.save(user);
    }
}