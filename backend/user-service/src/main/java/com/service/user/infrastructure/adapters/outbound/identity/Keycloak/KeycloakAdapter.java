package com.service.user.infrastructure.adapters.outbound.identity.Keycloak;

import java.util.List;
import java.util.Map;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;

import com.service.user.infrastructure.adapters.outbound.identity.Keycloak.mapper.KeycloakUserMapper;
import com.service.user.infrastructure.adapters.outbound.identity.model.ExternalUser;
import com.service.user.domain.ports.IdentityProviderPort;

public class KeycloakAdapter implements IdentityProviderPort {

    private final Keycloak keycloak;
    private final KeycloakUserMapper mapper;

    private final String realm = "streaming-app";

    public KeycloakAdapter(Keycloak keycloak,
                           KeycloakUserMapper mapper) {
        this.keycloak = keycloak;
        this.mapper = mapper;
    }

    @Override
    public ExternalUser getUserById(String id) {

        UserRepresentation rep = keycloak
                .realm(realm)
                .users()
                .get(id)
                .toRepresentation();

        return mapper.toExternal(
                rep,
                getRealmRoles(id),
                getClientRoles(id)
        );
    }

    @Override
    public ExternalUser getUserByEmail(String email) {

        List<UserRepresentation> users = keycloak
                .realm(realm)
                .users()
                .searchByEmail(email, true);

        if (users.isEmpty()) {
            return null;
        }

        UserRepresentation rep = users.get(0);

        return mapper.toExternal(
                rep,
                getRealmRoles(rep.getId()),
                getClientRoles(rep.getId())
        );
    }

    // =========================
    // Helpers
    // =========================

    private List<String> getRealmRoles(String userId) {

        return keycloak
                .realm(realm)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .listAll()
                .stream()
                .map(role -> role.getName())
                .toList();
    }

    private Map<String, List<String>> getClientRoles(String userId) {

        // Por ahora vacío
        // luego puedes implementar extracción real

        return Map.of();
    }
}
