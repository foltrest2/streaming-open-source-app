package com.service.user.infrastructure.adapters.outbound.persistance;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String iamId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private boolean active;

    protected UserEntity() {
        // JPA
    }

    public UserEntity(UUID id,
            String iamId,
            String email,
            String username,
            boolean active) {
        this.id = id;
        this.iamId = iamId;
        this.email = email;
        this.username = username;
        this.active = active;
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

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
