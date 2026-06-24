package com.service.user.infrastructure.adapters.outbound.persistance;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository {

    Optional<UserEntity> findById(UUID id);
    Optional<UserEntity> findByIamId(String iamId);
    Optional<UserEntity> findByEmail(String email);
    UserEntity save(UserEntity entity);
    void deleteById(UUID id);
}
