package com.service.user.domain.ports;

import com.service.user.infrastructure.adapters.outbound.identity.Keycloak.dto.ExternalUser;

interface IdentityProviderPort {
    ExternalUser getUserById(String id);
    ExternalUser getUserByEmail(String email);
}
