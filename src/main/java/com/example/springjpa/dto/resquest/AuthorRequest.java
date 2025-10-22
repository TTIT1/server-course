package com.example.springjpa.dto.resquest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class AuthorRequest {
      Integer id;
     String firstName;
     String lastName;
     String email;
     int age;
     List<Integer> courseIds; // chỉ chứa ID khoá học
}
