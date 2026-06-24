package com.service.user.infrastructure.adapters.inbound.dto.mapper;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.service.user.domain.model.User;
import com.service.user.domain.model.UserPreferences;
import com.service.user.infrastructure.adapters.inbound.dto.request.UpdateUserRequest;
import com.service.user.infrastructure.adapters.inbound.dto.request.UserPreferencesRequest;
import com.service.user.infrastructure.adapters.inbound.dto.response.UserMeResponse;
import com.service.user.infrastructure.adapters.inbound.dto.response.UserResponse;

@Component
public class UserDtoMapper {

    public User toDomain(UpdateUserRequest request) {

        Objects.requireNonNull(request, "request cannot be null");

        User user = new User();

        user.setName(request.getName());

        return user;
    }

    public UserPreferences toDomain(UserPreferencesRequest request) {

        Objects.requireNonNull(request, "request cannot be null");

        UserPreferences preferences = new UserPreferences();

        preferences.setFavoriteGenres(request.getFavoriteGenres());
        preferences.setLanguage(request.getLanguage());

        return preferences;
    }

    public UserResponse toUserResponse(User user) {

        Objects.requireNonNull(user, "user cannot be null");

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }

    public UserMeResponse toUserMeResponse(User user) {

        Objects.requireNonNull(user, "user cannot be null");

        UserPreferences preferences = user.getPreferences();

        return new UserMeResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                preferences != null ? preferences.getFavoriteGenres() : null,
                preferences != null ? preferences.getLanguage() : null
        );
    }
}