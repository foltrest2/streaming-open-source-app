package com.service.user.infrastructure.adapters.outbound.identity.Keycloak.mapper;

import java.util.List;
import java.util.Map;

import org.keycloak.representations.idm.UserRepresentation;

import com.service.user.infrastructure.adapters.outbound.identity.model.ExternalUser;

public class KeycloakUserMapper {

    public ExternalUser toExternal(UserRepresentation rep,
                                   List<String> realmRoles,
                                   Map<String, List<String>> clientRoles) {

        if (rep == null) {
            return null;
        }

        return new ExternalUser.Builder()
                .id(rep.getId())
                .username(rep.getUsername())
                .email(rep.getEmail())
                .firstName(rep.getFirstName())
                .lastName(rep.getLastName())
                .enabled(Boolean.TRUE.equals(rep.isEnabled()))
                .emailVerified(Boolean.TRUE.equals(rep.isEmailVerified()))
                .attributes(rep.getAttributes())
                .realmRoles(realmRoles)
                .clientRoles(clientRoles)
                .build();
    }
}
