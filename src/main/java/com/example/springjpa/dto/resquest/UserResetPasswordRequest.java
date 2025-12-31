package com.example.springjpa.dto.resquest;

import com.example.springjpa.validation.StrConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class UserResetPasswordRequest {
    @Email(regexp = ".+@.+\\..+", message = "Email need example@domain.com")
    @NotBlank
    String email;
    @StrConstraint(min =8,message = "INVALID_PASSWORD")
    String password;
    String confirmPassword;
}
