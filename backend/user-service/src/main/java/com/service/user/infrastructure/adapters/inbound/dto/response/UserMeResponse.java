package com.service.user.infrastructure.adapters.inbound.dto.response;

import java.util.List;
import java.util.UUID;

public class UserMeResponse {

    private UUID id;
    private String email;
    private String name;
    private List<String> favoriteGenres;
    private String language;

    public UserMeResponse() {}

    public UserMeResponse(UUID id, String email, String name,
                          List<String> favoriteGenres, String language) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.favoriteGenres = favoriteGenres;
        this.language = language;
    }

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public List<String> getFavoriteGenres() { return favoriteGenres; }
    public String getLanguage() { return language; }

    public void setId(UUID id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setFavoriteGenres(List<String> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }
    public void setLanguage(String language) { this.language = language; }
}