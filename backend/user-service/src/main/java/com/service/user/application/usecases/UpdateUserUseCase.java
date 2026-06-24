package com.service.user.application.usecases;

import com.service.user.domain.exception.UserNotFoundException;
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
                .orElseThrow(UserNotFoundException::new);

        user.setName(name);

        return userRepository.save(user);
    }
}