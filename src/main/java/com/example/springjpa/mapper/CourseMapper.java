package com.example.springjpa.mapper;


import com.example.springjpa.dto.resquest.CourseDTO;
import com.example.springjpa.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface CourseMapper {
  Course toCourse(CourseDTO courseDTO);
}
