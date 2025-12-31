package com.example.springjpa.service;


import com.example.springjpa.dto.resquest.CouresUserRequest;
import com.example.springjpa.dto.resquest.CourseDTO;
import com.example.springjpa.model.course.Course;

import java.util.List;


public interface CourseService {
    boolean DeleteById(String id);
    Course GetByID(String id);
    Course update(String id , CourseDTO courseDTO);
     List<CourseDTO> getCourse();
    Course save(CourseDTO courseDTO);
   //CourseDTO getFullCourse(CouresUserRequest  couresUserRequest);


}
