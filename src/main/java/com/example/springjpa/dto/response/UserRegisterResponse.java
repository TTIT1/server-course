package com.example.springjpa.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@RequiredArgsConstructor
public class UserRegisterResponse {
    boolean valid;
}
