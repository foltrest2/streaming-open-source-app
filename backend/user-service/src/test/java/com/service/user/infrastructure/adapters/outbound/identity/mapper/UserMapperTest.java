package com.service.user.infrastructure.adapters.outbound.identity.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.service.user.domain.model.User;
import com.service.user.infrastructure.adapters.outbound.identity.model.ExternalUser;

public class UserMapperTest {

    private final UserMapper mapper = new UserMapper();

    @Test
    void shouldMapExternalUserToDomain() {

        ExternalUser externalUser = new ExternalUser.Builder()
                .id("kc-123")
                .email("julian@test.com")
                .firstName("Julian")
                .enabled(true)
                .build();

        User result = mapper.toDomain(externalUser);

        assertNotNull(result);
        assertEquals("kc-123", result.getIamId());
        assertEquals("julian@test.com", result.getEmail());
        assertEquals("Julian", result.getName());
    }

    @Test
    void shouldMapDomainToExternalUser() {

        User user = new User();
        user.setIamId("kc-123");
        user.setEmail("julian@test.com");

        ExternalUser result = mapper.toExternal(user);

        assertNotNull(result);
        assertEquals("kc-123", result.getId());
    }

    @Test
    void shouldReturnNullWhenExternalUserIsNull() {

        User result = mapper.toDomain(null);

        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenUserIsNull() {

        ExternalUser result = mapper.toExternal(null);

        assertNull(result);
    }
}
