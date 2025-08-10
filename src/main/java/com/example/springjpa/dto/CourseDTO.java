package com.example.springjpa.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CourseDTO {
   private Integer id;
    private String title;
    private String description;
    private List<Integer> authorIds; // chỉ chứa ID tác giả
}
