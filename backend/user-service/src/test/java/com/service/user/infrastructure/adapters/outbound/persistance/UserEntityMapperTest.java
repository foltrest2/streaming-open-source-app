package com.service.user.infrastructure.adapters.outbound.persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.service.user.domain.model.User;
import com.service.user.domain.model.UserPreferences;
import com.service.user.domain.model.UserStatus;

public class UserEntityMapperTest {

    private final UserEntityMapper mapper = new UserEntityMapper();
    private final UUID id = UUID.randomUUID();
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(
                id,
                "iam-123",
                "julian@test.com",
                "julian",
                new UserPreferences(List.of("action"), "es"),
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now(),
                true);
    }

    @Test
    void shouldMapDomainToEntity() {
        UserEntity entity = mapper.toEntity(user);

        assertNotNull(entity);

        assertEquals(id, entity.getId());
        assertEquals("iam-123", entity.getIamId());
        assertEquals("julian@test.com", entity.getEmail());
        assertEquals("julian", entity.getUsername());
        assertTrue(entity.isActive());
    }

    @Test
    void shouldMapEntityToDomain() {

        UserEntity entity = new UserEntity(
                id,
                "iam-123",
                "julian@test.com",
                "julian",
                true);

        User user = mapper.toDomain(entity);

        assertNotNull(user);

        assertEquals(id, user.getId());
        assertEquals("iam-123", user.getIamId());
        assertEquals("julian@test.com", user.getEmail());
        assertEquals("julian", user.getName());
        assertTrue(user.isActive());
    }

    @Test
    void shouldReturnNullWhenUserIsNull() {

        UserEntity entity = mapper.toEntity(null);

        assertNull(entity);
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {

        User user = mapper.toDomain(null);

        assertNull(user);
    }

    @Test
    void shouldMapInactiveUserCorrectly() {

        user.setActive(false);

        UserEntity entity = mapper.toEntity(user);

        assertNotNull(entity);
        assertFalse(entity.isActive());
    }
}
