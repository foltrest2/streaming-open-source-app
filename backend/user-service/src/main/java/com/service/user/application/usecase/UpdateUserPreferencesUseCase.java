package com.service.user.application.usecase;

import com.service.user.domain.model.User;
import com.service.user.domain.model.UserPreferences;
import com.service.user.domain.ports.UserRepositoryPort;

import java.util.List;
import java.util.UUID;

public class UpdateUserPreferencesUseCase {

    private final UserRepositoryPort userRepository;

    public UpdateUserPreferencesUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UUID userId, List<String> genres, String language) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserPreferences preferences = new UserPreferences();
        preferences.setFavoriteGenres(genres);
        preferences.setLanguage(language);

        user.setPreferences(preferences);

        return userRepository.save(user);
    }
}