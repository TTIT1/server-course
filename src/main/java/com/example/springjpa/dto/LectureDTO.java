package com.example.springjpa.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LectureDTO {
    private Integer id;
    private String name;
    private Integer sectionId;  // hoặc sectionName nếu cần

}
