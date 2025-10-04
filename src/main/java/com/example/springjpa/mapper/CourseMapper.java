package com.example.springjpa.mapper;


import com.example.springjpa.dto.resquest.CourseDTO;
import com.example.springjpa.model.Author;
import com.example.springjpa.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel ="spring")
public interface CourseMapper {
  Course toCourse(CourseDTO courseDTO);
    Course updateCourse(@MappingTarget Course course, CourseDTO courseDTO);
}
