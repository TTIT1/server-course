package com.example.springjpa.mapper;


import com.example.springjpa.dto.resquest.CourseDTO;
import com.example.springjpa.dto.resquest.LectureDTO;
import com.example.springjpa.dto.resquest.ResourceDTO;
import com.example.springjpa.dto.resquest.SectionDTO;
import com.example.springjpa.model.course.Course;
import com.example.springjpa.model.course.Lecture;
import com.example.springjpa.model.course.Resource;
import com.example.springjpa.model.course.Section;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel ="spring")
public interface CourseMapper {
    Course toCourse(CourseDTO courseDTO);
    Course updateCourse(@MappingTarget Course course, CourseDTO courseDTO);
    CourseDTO toDto(Course course);

    SectionDTO toSectionDto(Section section);

    LectureDTO toLectureDto(Lecture lecture);

    Set<SectionDTO> toSectionDtos(Set<Section> sections);

    List<LectureDTO> toLectureDtos(Set<Lecture> lectures);

    Set<ResourceDTO> toResourceDtos(Set<Resource> resources);

}
