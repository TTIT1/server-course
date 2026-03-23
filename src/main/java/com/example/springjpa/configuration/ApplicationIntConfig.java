package com.example.springjpa.configuration;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springjpa.enums.Roles;
import com.example.springjpa.model.auth.Permission;
import com.example.springjpa.model.auth.Role;
import com.example.springjpa.model.auth.User;
import com.example.springjpa.repository.PermissionRepository;
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
    PermissionRepository permissionRepository;

    private Permission createPermissionIfNotExists(String name, String description) {
        return permissionRepository.findById(name)
                .orElseGet(() -> {
                    Permission permission = new Permission();
                    permission.setName(name);
                    permission.setDescription(description);
                    return permissionRepository.save(permission);
                });
    }

    @Bean
    @Order(1)
    ApplicationRunner initAdmin(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {
                // Khởi tạo các permission cơ bản trong DB (chỉ chạy khi chưa có admin)
                Set<Permission> adminPermissions = new HashSet<>();
                adminPermissions.add(createPermissionIfNotExists("COURSE_READ", "Xem thông tin khóa học"));
                adminPermissions.add(createPermissionIfNotExists("COURSE_MANAGE", "Quản lý khóa học"));
                adminPermissions.add(createPermissionIfNotExists("USER_MANAGE", "Quản lý người dùng"));
                adminPermissions.add(createPermissionIfNotExists("ORDER_MANAGE", "Quản lý đơn hàng"));

                // Tạo hoặc lấy role ADMIN gán kèm permissions
                Role roleAdmin = roleRepositoty.findById(Roles.ADMIN.name())
                        .orElseGet(() -> {
                            Role r = new Role();
                            r.setName(Roles.ADMIN.name());
                            r.setDescription("Quản trị hệ thống");
                            r.setPermissions(adminPermissions);
                            return roleRepositoty.save(r);
                        });

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
