package com.example.springjpa.dto.resquest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class AuthorRequest {
     String id;
     String firstName;
     String lastName;
     String email;
     String password;
     int age;

     List<String> courseIds; // chỉ chứa ID khoá học
}
