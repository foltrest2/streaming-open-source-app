package com.service.user.application.usecases;

import com.service.user.domain.exception.UserNotFoundException;
import com.service.user.domain.model.User;
import com.service.user.domain.ports.UserRepositoryPort;

public class GetUserByKeycloakIdUseCase {

    private final UserRepositoryPort userRepository;

    public GetUserByKeycloakIdUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String iamId) {
        return userRepository.findByIamId(iamId)
                .orElseThrow(UserNotFoundException::new);
    }
}