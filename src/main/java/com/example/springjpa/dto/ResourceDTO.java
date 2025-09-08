package com.example.springjpa.dto;



import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceDTO {
    private Integer id;
    private String name;
    private int size;
    private String url;
    private Integer lectureid;
}
