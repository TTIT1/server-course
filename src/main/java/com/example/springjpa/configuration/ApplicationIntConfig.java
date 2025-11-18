package com.example.springjpa.configuration;


import com.example.springjpa.enums.Roles;

import com.example.springjpa.model.auth.RefreshToken;
import com.example.springjpa.model.auth.Role;
import com.example.springjpa.model.auth.User;
import com.example.springjpa.repository.RefreshTokenRepository;
import com.example.springjpa.repository.RoleRepositoty;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.security.JwtUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class ApplicationIntConfig {
    PasswordEncoder passwordEncoder;
RoleRepositoty roleRepositoty;
    @Bean
    @Order(1)
    ApplicationRunner initAdmin(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {
                Role roleAdmin = new Role();
                roleAdmin.setName(Roles.ADMIN.name());
                roleAdmin.setDescription("Quản trị hệ thống");
                roleRepositoty.save(roleAdmin);
                User user = User.builder()
                        .userName("admin")
                         .roles(Set.of(roleAdmin))
                        .passwordUser(passwordEncoder.encode("admin123"))
                        .gmail("admin@gmail.com")
                        .build();
                userRepository.save(user);
                log.warn("Created user admin: " + user);
            }
        };
    }



//
////    @Bean
////    @Order(2)
////    ApplicationRunner cleanExpiredTokens(RefreshTokenRepository refreshTokenRepository) {
////        return args -> {
////            List<RefreshToken> refreshTokens = refreshTokenRepository.findAll();
////            if (!refreshTokens.isEmpty()) {
////                for (RefreshToken token : refreshTokens) {
////                    RefreshTokenRequest refreshTokenRequest =
////                            new RefreshTokenRequest(token.getRefreshToken(), token.getUser().getId());
////                    boolean valid = JwtUtil.validateRefreshToken(refreshTokenRequest);
////                    if (valid) {
////                        refreshTokenRepository.deleteById(token.getId());
////                        log.warn("delete refresh token timeout");
////                    }
////                }
////            }
////        };
////    }
//
}
