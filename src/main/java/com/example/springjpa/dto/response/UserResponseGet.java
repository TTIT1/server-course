package com.example.springjpa.dto.response;

import com.example.springjpa.model.auth.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@AllArgsConstructor
public class UserResponseGet {
    String gmail;
    String userName;
    Set<Role> roles;
}
