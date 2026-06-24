package com.service.user.infrastructure.adapters.outbound.persistance;

import com.service.user.domain.model.User;

public class UserEntityMapper {
    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();

        entity.setId(user.getId());
        entity.setIamId(user.getIamId());
        entity.setEmail(user.getEmail());
        entity.setUsername(user.getName());
        entity.setActive(user.isActive());

        return entity;
    }

    public User toDomain(UserEntity entity) {

        if (entity == null) {
            return null;
        }

        User user = new User();

        user.setId(entity.getId());
        user.setIamId(entity.getIamId());
        user.setEmail(entity.getEmail());
        user.setName(entity.getUsername());
        user.setActive(entity.isActive());

        return user;
    }
}
