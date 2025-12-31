package com.example.springjpa.dto.resquest;

import com.example.springjpa.validation.DobConstraint;
import com.example.springjpa.validation.StrConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestregister {
    @Email(regexp = ".+@.+\\..+", message = "Email phải có dạng example@domain.com")
    String gmail;
    @StrConstraint(min = 8,message = "INVALID_PASSWORD")
    String PassWordUser;
  @DobConstraint(min = 16,message = "INVALID_AGE")
    LocalDate  birthDate;
    String UserName;
}
