package com.example.springjpa.service;

import com.example.springjpa.dto.CourseDTO;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Author;
import com.example.springjpa.model.Course;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public interface CourseService {
    boolean DeleteById(Integer id);
    Course GetByID(Integer id);
    Course update(Integer id , CourseDTO courseDTO);
     List<Course> getCourse();
    Course save(CourseDTO courseDTO);


}
