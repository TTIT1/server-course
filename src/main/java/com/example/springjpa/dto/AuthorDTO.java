package com.example.springjpa.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthorDTO {
    private  Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private List<Integer> courseIds; // chỉ chứa ID khoá học
}
