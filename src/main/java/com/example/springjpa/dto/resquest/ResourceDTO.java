package com.example.springjpa.dto.resquest;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceDTO {
     Integer id;
     String name;
     int size;
     String url;
     Integer lectureid;
}
