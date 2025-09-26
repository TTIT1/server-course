package com.example.springjpa.service;

import com.example.springjpa.dto.CourseDTO;
import com.example.springjpa.model.Course;

import java.util.List;


public interface CourseService {
    boolean DeleteById(Integer id);
    Course GetByID(Integer id);
    Course update(Integer id , CourseDTO courseDTO);
     List<CourseDTO> getCourse();
    Course save(CourseDTO courseDTO);


}
