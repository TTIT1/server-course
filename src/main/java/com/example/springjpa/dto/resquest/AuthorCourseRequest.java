package com.example.springjpa.dto.resquest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AuthorCourseRequest {
    String firstName;
    String lastName;
    String email;
    int age;
    String title;
    String description;
    Integer Authorid;
    Integer Courseid;
}
