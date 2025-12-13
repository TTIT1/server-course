package com.example.springjpa.dto.resquest;
import com.example.springjpa.validation.DobConstraint;
import com.example.springjpa.validation.StrConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.loader.ast.spi.Loadable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class AuthorRequest {

     @StrConstraint(min = 4,message = "INVALID_CREDENTIALS")
     String firstName;
    @StrConstraint(min = 4,message = "INVALID_CREDENTIALS")
     String lastName;
    @Email(regexp = ".+@.+\\..+", message = "Email phải có dạng example@domain.com")
     String email;
    @StrConstraint(min = 8,message = "INVALID_PASSWORD")
     String password;
    @DobConstraint(min = 23 ,message = "INVALID_AGE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     LocalDate dob;

     List<String> courseIds; // chỉ chứa ID khoá học
}
