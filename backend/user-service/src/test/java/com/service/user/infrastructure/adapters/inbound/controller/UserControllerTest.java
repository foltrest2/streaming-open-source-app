package com.service.user.infrastructure.adapters.inbound.controller;
/* 
import static org.mockito.Mockito.when;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.user.application.usecases.DeleteUserUseCase;
import com.service.user.application.usecases.GetOrCreateCurrentUserUseCase;
import com.service.user.application.usecases.GetUserByIdUseCase;
import com.service.user.application.usecases.UpdateUserPreferencesUseCase;
import com.service.user.application.usecases.UpdateUserUseCase;
import com.service.user.domain.model.User;
import com.service.user.infrastructure.adapters.inbound.dto.mapper.UserDtoMapper;
import com.service.user.infrastructure.adapters.inbound.dto.request.UpdateUserRequest;
*/
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {
/* 
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private GetUserByIdUseCase getUserByIdUseCase;

    @MockitoBean
    private GetOrCreateCurrentUserUseCase getOrCreateUserUseCase;

    @MockitoBean
    private UpdateUserUseCase updateUserUseCase;

    @MockitoBean
    private UpdateUserPreferencesUseCase updateUserPreferencesUseCase;

    @MockitoBean
    private DeleteUserUseCase deleteUserUseCase;

    @MockitoBean
    private UserDtoMapper mapper;

    @Test
    void shouldReturnUserById() throws Exception {

        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);
        user.setEmail("julian@test.com");
        user.setName("Julian");

        when(getUserByIdUseCase.execute(userId))
                .thenReturn(user);      

        mockMvc.perform(
                get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(userId.toString()))
                .andExpect(jsonPath("$.email")
                        .value("julian@test.com"))
                .andExpect(jsonPath("$.name")
                        .value("Julian"));
    }

    @Test
    void shouldDeleteUser() throws Exception {

        UUID userId = UUID.randomUUID();

        mockMvc.perform(
                delete("/users/{id}", userId))
                .andExpect(status().isNoContent());
    }

    @SuppressWarnings("null")
    @Test
    void shouldUpdateUser() throws Exception {

        UUID userId = UUID.randomUUID();

        UpdateUserRequest request = new UpdateUserRequest("Jose");

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setName("Jose");

        when(updateUserUseCase.execute(
                userId,
                request.getName()))
                .thenReturn(updatedUser);

        mockMvc.perform(
                put("/users/{id}", userId)
                        .contentType("application/json")
                        .content(
                                objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("Jose"));
    }

    */
}