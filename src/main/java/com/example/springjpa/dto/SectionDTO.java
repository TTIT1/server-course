package com.example.springjpa.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SectionDTO {
    private Integer id;
    private String name;
    private int order;
    private Integer courseId;

}
