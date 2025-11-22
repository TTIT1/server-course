package com.example.springjpa.dto.resquest;

import com.example.springjpa.validation.DobConstraint;
import com.example.springjpa.validation.StrConstraint;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AuthorCourseRequest {
    @StrConstraint(min = 2,message = "INVALID_USERNAME")
    String firstName;
    @StrConstraint(min = 2,message = "INVALID_USERNAME")
    String lastName;
    String email;
    @DobConstraint(min = 23,message = "INVALID_AGE")
    LocalDate age;
    String title;
    String description;
    Integer Authorid;
    Integer Courseid;
}
