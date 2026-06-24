package com.service.user.infrastructure.adapters.inbound.controller;

import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.user.application.usecases.DeleteUserUseCase;
import com.service.user.application.usecases.GetOrCreateCurrentUserUseCase;
import com.service.user.application.usecases.GetUserByIdUseCase;
import com.service.user.application.usecases.UpdateUserPreferencesUseCase;
import com.service.user.application.usecases.UpdateUserUseCase;
import com.service.user.domain.model.User;
import com.service.user.infrastructure.adapters.inbound.dto.request.UpdateUserRequest;
import com.service.user.infrastructure.adapters.inbound.dto.request.UserPreferencesRequest;
import com.service.user.infrastructure.adapters.inbound.dto.response.UserMeResponse;
import com.service.user.infrastructure.adapters.inbound.dto.response.UserResponse;
import com.service.user.infrastructure.adapters.inbound.dto.mapper.UserDtoMapper;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetOrCreateCurrentUserUseCase getOrCreateCurrentUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final UpdateUserPreferencesUseCase updateUserPreferencesUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserDtoMapper mapper;

    public UserController(
            GetUserByIdUseCase getUserByIdUseCase,
            GetOrCreateCurrentUserUseCase getOrCreateCurrentUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            UpdateUserPreferencesUseCase updateUserPreferencesUseCase,
            DeleteUserUseCase deleteUserUseCase,
            UserDtoMapper mapper) {

        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getOrCreateCurrentUserUseCase = getOrCreateCurrentUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.updateUserPreferencesUseCase = updateUserPreferencesUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.mapper = mapper;
    }

    @GetMapping("/me")
    public ResponseEntity<UserMeResponse> getCurrentUser(
            JwtAuthenticationToken authentication) {

        Jwt jwt = authentication.getToken();

        String iamId = jwt.getSubject();
        String email = jwt.getClaimAsString("email");
        String name = jwt.getClaimAsString("name");

        User user = getOrCreateCurrentUserUseCase.execute(
                iamId,
                email,
                name
        );

        return ResponseEntity.ok(
                mapper.toUserMeResponse(user)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable UUID id) {

        User user = getUserByIdUseCase.execute(id);

        return ResponseEntity.ok(
                mapper.toUserResponse(user)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUserRequest request) {

        User user = updateUserUseCase.execute(
                id,
                request.getName()
        );

        return ResponseEntity.ok(
                mapper.toUserResponse(user)
        );
    }

    @PutMapping("/{id}/preferences")
    public ResponseEntity<UserResponse> updatePreferences(
            @PathVariable UUID id,
            @Valid @RequestBody UserPreferencesRequest request) {

        User user = updateUserPreferencesUseCase.execute(
                id,
                request.getFavoriteGenres(),
                request.getLanguage()
        );

        return ResponseEntity.ok(
                mapper.toUserResponse(user)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable UUID id) {

        deleteUserUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }
}