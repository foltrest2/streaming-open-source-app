package com.service.user.infrastructure.adapters.outbound.identity.mapper;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.service.user.domain.model.User;
import com.service.user.domain.model.UserPreferences;
import com.service.user.domain.model.UserStatus;
import com.service.user.infrastructure.adapters.outbound.identity.model.ExternalUser;

public class UserMapper {

   public User toDomain(ExternalUser external) {

        if (external == null) {
            return null;
        }

        User user = new User();

        user.setId(UUID.randomUUID());

        user.setIamId(external.getId());

        user.setEmail(normalizeEmail(external.getEmail()));

        user.setName(buildName(
                external.getFirstName(),
                external.getLastName()
        ));

        user.setPreferences(defaultPreferences());

        user.setStatus(resolveStatus(external));

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }

    public ExternalUser toExternal(User user) {

        if (user == null) {
            return null;
        }

        String[] names = splitName(user.getName());

        return new ExternalUser.Builder()
                .id(user.getIamId())
                .email(user.getEmail())
                .firstName(names[0])
                .lastName(names[1])
                .enabled(user.getStatus() == UserStatus.ACTIVE)
                .emailVerified(false)
                .attributes(Map.of())
                .realmRoles(List.of())
                .clientRoles(Map.of())
                .build();
    }

    // =========================
    // Helpers
    // =========================

    private String normalizeEmail(String email) {

        if (email == null) {
            return null;
        }

        return email.trim().toLowerCase();
    }

    private String[] splitName(String fullName) {

        if (fullName == null || fullName.isBlank()) {
            return new String[]{"", ""};
        }

        String[] parts = fullName.trim().split(" ", 2);

        String firstName = parts[0];
        String lastName = parts.length > 1 ? parts[1] : "";

        return new String[]{firstName, lastName};
    }

    private String buildName(String firstName, String lastName) {

        String first = safe(firstName);
        String last = safe(lastName);

        return (first + " " + last).trim();
    }

    private String safe(String value) {

        if (value == null) {
            return "";
        }

        return value.trim();
    }

    private UserPreferences defaultPreferences() {

        return new UserPreferences();
    }

    private UserStatus resolveStatus(ExternalUser external) {

        return external.isEnabled()
                ? UserStatus.ACTIVE
                : UserStatus.INACTIVE;
    }
}
