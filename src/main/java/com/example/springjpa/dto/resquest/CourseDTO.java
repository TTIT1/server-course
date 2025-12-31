package com.example.springjpa.dto.resquest;
import com.example.springjpa.model.course.Lecture;
import com.example.springjpa.model.course.Section;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CourseDTO {

     String title;
     String description;
     List<String> authorIds;
     Set<SectionDTO> sections;
     Set<LectureDTO> lectures;
     Set<ResourceDTO>resourceDTOS;
     Set<TextDTO>textDTOS;
     Set<VideoDTO>videoDTOS;


}
