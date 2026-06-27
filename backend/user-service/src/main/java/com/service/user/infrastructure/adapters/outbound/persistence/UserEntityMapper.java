package com.service.user.infrastructure.adapters.outbound.persistence;

import com.service.user.domain.model.User;

public class UserEntityMapper {
    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();

        entity.setId(user.getId());
        entity.setIamId(user.getIamId());
        entity.setEmail(user.getEmail());
        entity.setName(user.getName());

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
        user.setName(entity.getName());

        return user;
    }
}
