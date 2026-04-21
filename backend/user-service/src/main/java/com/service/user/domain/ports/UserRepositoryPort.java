package com.service.user.domain.ports;

import java.util.Optional;
import java.util.UUID;

import com.service.user.domain.model.User;

public interface UserRepositoryPort {
    Optional<User> findById(UUID id);
    Optional<User> findByKeycloakId(String keycloakId);
    User save(User user);
    void deleteById(UUID id);
}