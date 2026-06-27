package com.service.user.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.service.user.application.usecases.DeleteUserUseCase;
import com.service.user.application.usecases.GetOrCreateCurrentUserUseCase;
import com.service.user.application.usecases.GetUserByIdUseCase;
import com.service.user.application.usecases.UpdateUserPreferencesUseCase;
import com.service.user.application.usecases.UpdateUserUseCase;
import com.service.user.domain.ports.UserRepositoryPort;

@Configuration
public class UseCaseConfig {

    @Bean
    GetUserByIdUseCase getUserByIdUseCase(
            UserRepositoryPort userRepository) {

        return new GetUserByIdUseCase(userRepository);
    }

    @Bean
    GetOrCreateCurrentUserUseCase getOrCreateCurrentUserUseCase(
            UserRepositoryPort userRepository) {

        return new GetOrCreateCurrentUserUseCase(
                userRepository
        );
    }

    @Bean
    UpdateUserUseCase updateUserUseCase(
            UserRepositoryPort userRepository) {

        return new UpdateUserUseCase(userRepository);
    }

    @Bean
    UpdateUserPreferencesUseCase updateUserPreferencesUseCase(
            UserRepositoryPort userRepository) {

        return new UpdateUserPreferencesUseCase(userRepository);
    }

    @Bean
    DeleteUserUseCase deleteUserUseCase(
            UserRepositoryPort userRepository) {

        return new DeleteUserUseCase(userRepository);
    }
}