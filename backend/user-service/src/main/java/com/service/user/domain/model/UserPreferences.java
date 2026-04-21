package com.service.user.domain.model;

import java.util.List;

public class UserPreferences {

    private List<String> favoriteGenres;
    private String language;

    public UserPreferences() {}

    public UserPreferences(List<String> favoriteGenres, String language) {
        this.favoriteGenres = favoriteGenres;
        this.language = language;
    }

    public List<String> getFavoriteGenres() { return favoriteGenres; }
    public String getLanguage() { return language; }

    public void setFavoriteGenres(List<String> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}