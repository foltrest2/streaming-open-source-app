package com.service.user.infrastructure.adapters.outbound.identity.model;

import java.util.List;
import java.util.Map;

import com.service.user.infrastructure.adapters.outbound.identity.model.ExternalUser;

public class ExternalUser {

    private String id; // ID de Keycloak (UUID)
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    private boolean enabled;
    private boolean emailVerified;

    // Atributos custom de Keycloak
    private Map<String, List<String>> attributes;

    // Roles (dependiendo de cómo los uses)
    private List<String> realmRoles;
    private Map<String, List<String>> clientRoles;

    // Constructor privado para forzar uso de builder
    private ExternalUser(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.enabled = builder.enabled;
        this.emailVerified = builder.emailVerified;
        this.attributes = builder.attributes;
        this.realmRoles = builder.realmRoles;
        this.clientRoles = builder.clientRoles;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public Map<String, List<String>> getAttributes() {
        return attributes;
    }

    public List<String> getRealmRoles() {
        return realmRoles;
    }

    public Map<String, List<String>> getClientRoles() {
        return clientRoles;
    }

    // =========================
    // Builder
    // =========================
    public static class Builder {
        private String id;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private boolean enabled;
        private boolean emailVerified;
        private Map<String, List<String>> attributes;
        private List<String> realmRoles;
        private Map<String, List<String>> clientRoles;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder emailVerified(boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        public Builder attributes(Map<String, List<String>> attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder realmRoles(List<String> realmRoles) {
            this.realmRoles = realmRoles;
            return this;
        }

        public Builder clientRoles(Map<String, List<String>> clientRoles) {
            this.clientRoles = clientRoles;
            return this;
        }

        public ExternalUser build() {
            return new ExternalUser(this);
        }
    }
}
