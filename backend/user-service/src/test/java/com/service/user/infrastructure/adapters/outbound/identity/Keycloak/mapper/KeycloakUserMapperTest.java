package com.service.user.infrastructure.adapters.outbound.identity.Keycloak.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;

import com.service.user.infrastructure.adapters.outbound.identity.model.ExternalUser;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class KeycloakUserMapperTest {

    private KeycloakUserMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new KeycloakUserMapper();
    }

    @Test
    void shouldMapUserRepresentationToExternalUser() {

        UserRepresentation rep = new UserRepresentation();

        rep.setId("123");
        rep.setUsername("julian");
        rep.setEmail("julian@test.com");
        rep.setFirstName("Julian");
        rep.setLastName("Riascos");
        rep.setEnabled(true);
        rep.setEmailVerified(true);

        Map<String, List<String>> attributes = Map.of(
                "country", List.of("CO")
        );

        rep.setAttributes(attributes);

        ExternalUser result = mapper.toExternal(
                rep,
                List.of("USER"),
                Map.of("streaming-app", List.of("ADMIN"))
        );

        assertNotNull(result);

        assertEquals("123", result.getId());
        assertEquals("julian", result.getUsername());
        assertEquals("julian@test.com", result.getEmail());
        assertEquals("Julian", result.getFirstName());
        assertEquals("Riascos", result.getLastName());

        assertTrue(result.isEnabled());
        assertTrue(result.isEmailVerified());

        assertEquals("CO",
                result.getAttributes().get("country").get(0));

        assertEquals("USER",
                result.getRealmRoles().get(0));

        assertEquals("ADMIN",
                result.getClientRoles()
                        .get("streaming-app")
                        .get(0));
    }

    @Test
    void shouldReturnNullWhenRepresentationIsNull() {

        ExternalUser result = mapper.toExternal(
                null,
                List.of(),
                Map.of()
        );

        assertNull(result);
    }
}