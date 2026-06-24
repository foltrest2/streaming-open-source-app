package com.service.user.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {

    private UUID id;
    private String iamId;
    private String email;
    private String name;
    private UserPreferences preferences;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;

    public User() {
    }

    public User(UUID id, String iamId, String email, String name,
            UserPreferences preferences, UserStatus status,
            LocalDateTime createdAt, LocalDateTime updatedAt, boolean active) {
        this.id = id;
        this.iamId = iamId;
        this.email = email;
        this.name = name;
        this.preferences = preferences;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public UUID getId() {
        return id;
    }

    public String getIamId() {
        return iamId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserPreferences getPreferences() {
        return preferences;
    }

    public UserStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setIamId(String iamId) {
        this.iamId = iamId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreferences(UserPreferences preferences) {
        this.preferences = preferences;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}