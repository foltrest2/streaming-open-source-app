package com.service.user.infrastructure.adapters.outbound.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID>{

    @SuppressWarnings("null")
    Optional<UserEntity> findById(UUID id);
    Optional<UserEntity> findByIamId(String iamId);
    Optional<UserEntity> findByEmail(String email);
    @SuppressWarnings({ "null", "unchecked" })
    UserEntity save(UserEntity entity);
    @SuppressWarnings("null")
    void deleteById(UUID id);
}
