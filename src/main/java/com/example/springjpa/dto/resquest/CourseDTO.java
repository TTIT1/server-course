package com.example.springjpa.dto.resquest;
import java.math.BigDecimal;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CourseDTO {

     String title;
     String description;
     List<String> authorIds;
     BigDecimal price;
     // Set<SectionDTO> sections;
     // Set<LectureDTO> lectures;
     // Set<ResourceDTO>resourceDTOS;
     // Set<TextDTO>textDTOS;
     // Set<VideoDTO>videoDTOS;


}
