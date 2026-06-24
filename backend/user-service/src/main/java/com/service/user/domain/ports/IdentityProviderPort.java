package com.service.user.domain.ports;

import com.service.user.infrastructure.adapters.outbound.identity.model.ExternalUser;

public interface IdentityProviderPort {
    ExternalUser getUserById(String id);
    ExternalUser getUserByEmail(String email);
}
