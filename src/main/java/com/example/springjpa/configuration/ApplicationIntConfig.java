package com.example.springjpa.configuration;


import java.time.LocalDate;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springjpa.enums.Roles;
import com.example.springjpa.model.auth.Role;
import com.example.springjpa.model.auth.User;
import com.example.springjpa.repository.RoleRepositoty;
import com.example.springjpa.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

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
                        .dob(LocalDate.of(2000,11,20))
                         .roles(Set.of(roleAdmin))
                        .passwordUser(passwordEncoder.encode("admin123"))
                        .gmail("admin@gmail.com")
                        .build();
                userRepository.save(user);
                log.warn("Created user admin: " + user);
            }
        };
    }



}
