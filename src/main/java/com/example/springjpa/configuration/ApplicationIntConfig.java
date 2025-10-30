package com.example.springjpa.configuration;

import com.example.springjpa.dto.resquest.RefreshTokenRequest;
import com.example.springjpa.enums.Roles;
import com.example.springjpa.model.RefreshToken;
import com.example.springjpa.model.User;
import com.example.springjpa.repository.RefreshTokenRepository;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.security.JwtUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ApplicationIntConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    @Order(1)
    ApplicationRunner initAdmin(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Roles.ADMIN.name());

                User user = User.builder()
                        .userName("admin")
                        .roles(roles)
                        .passwordUser(passwordEncoder.encode("admin123"))
                        .gmail("admin@gmail.com")
                        .build();
                userRepository.save(user);
                log.warn("Created user admin: " + user);
            }
        };
    }

    @Bean
    @Order(2)
    ApplicationRunner cleanExpiredTokens(RefreshTokenRepository refreshTokenRepository) {
        return args -> {
            List<RefreshToken> refreshTokens = refreshTokenRepository.findAll();
            if (!refreshTokens.isEmpty()) {
                for (RefreshToken token : refreshTokens) {
                    RefreshTokenRequest refreshTokenRequest =
                            new RefreshTokenRequest(token.getRefreshToken(), token.getUser().getId());
                    boolean valid = JwtUtil.validateRefreshToken(refreshTokenRequest);
                    if (valid) {
                        refreshTokenRepository.deleteById(token.getId());
                        log.warn("delete refresh token timeout");
                    }
                }
            }
        };
    }

}
