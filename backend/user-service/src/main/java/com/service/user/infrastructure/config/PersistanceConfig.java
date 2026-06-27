package com.service.user.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.service.user.domain.ports.UserRepositoryPort;
import com.service.user.infrastructure.adapters.outbound.persistence.UserEntityMapper;
import com.service.user.infrastructure.adapters.outbound.persistence.UserJpaRepository;
import com.service.user.infrastructure.adapters.outbound.persistence.UserJpaRepositoryAdapter;

@Configuration
public class PersistanceConfig {

    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserRepositoryPort userRepositoryPort(
            UserJpaRepository repository,
            UserEntityMapper mapper) {

        return new UserJpaRepositoryAdapter(repository, mapper);
    }
}