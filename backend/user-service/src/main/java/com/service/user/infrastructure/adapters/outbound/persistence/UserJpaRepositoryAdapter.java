package com.service.user.infrastructure.adapters.outbound.persistence;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.service.user.domain.model.User;
import com.service.user.domain.ports.UserRepositoryPort;

public class UserJpaRepositoryAdapter implements UserRepositoryPort {

    private UserJpaRepository repository;
    private UserEntityMapper mapper;

    public UserJpaRepositoryAdapter(
            UserJpaRepository repository,
            UserEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        Objects.requireNonNull(user, "user cannot be null");
        UserEntity entity = mapper.toEntity(user);
        UserEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> findByIamId(String iamId) {
        return repository.findByIamId(iamId).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }
}
