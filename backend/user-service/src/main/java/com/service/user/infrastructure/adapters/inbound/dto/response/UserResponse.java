package com.service.user.infrastructure.adapters.inbound.dto.response;

import java.util.UUID;

public class UserResponse {

    private UUID id;
    private String email;
    private String name;

    public UserResponse() {}

    public UserResponse(UUID id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }

    public void setId(UUID id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
}