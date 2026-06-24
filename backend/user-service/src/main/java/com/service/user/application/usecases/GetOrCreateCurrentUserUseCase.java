package com.service.user.application.usecases;

import com.service.user.domain.model.User;
import com.service.user.domain.model.UserStatus;
import com.service.user.domain.ports.UserRepositoryPort;

import java.util.UUID;

public class GetOrCreateCurrentUserUseCase {

    private final UserRepositoryPort userRepository;

    public GetOrCreateCurrentUserUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String iamId, String email, String name) {

        return userRepository.findByIamId(iamId)
                .orElseGet(() -> {
                    User user = new User();
                    user.setId(UUID.randomUUID());
                    user.setIamId(iamId);
                    user.setEmail(email);
                    user.setName(name);
                    user.setStatus(UserStatus.ACTIVE);
                    return userRepository.save(user);
                });
    }
}