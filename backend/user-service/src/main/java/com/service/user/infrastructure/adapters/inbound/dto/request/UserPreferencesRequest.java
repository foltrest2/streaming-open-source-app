package com.service.user.infrastructure.adapters.inbound.dto.request;

import java.util.List;

public class UserPreferencesRequest {

    private List<String> favoriteGenres;
    private String language;

    public UserPreferencesRequest() {}

    public UserPreferencesRequest(List<String> favoriteGenres, String language) {
        this.favoriteGenres = favoriteGenres;
        this.language = language;
    }

    public List<String> getFavoriteGenres() { return favoriteGenres; }
    public String getLanguage() { return language; }

    public void setFavoriteGenres(List<String> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public void setLanguage(String language) { this.language = language; }
}