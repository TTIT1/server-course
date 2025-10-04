package com.example.springjpa.service.Impl;

import com.example.springjpa.dto.resquest.CourseDTO;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.CourseMapper;
import com.example.springjpa.model.Author;
import com.example.springjpa.model.Course;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;
import com.example.springjpa.service.CourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseServiceImpl implements CourseService {
  CourseMapper courseMapper;
  CourseRepository courseRepository;
  AuthorRepository authorRepository;

  public CourseDTO toModelCourse(Course course) {
    CourseDTO courseDTO = new CourseDTO();
    courseDTO.setDescription(course.getDescription());
    courseDTO.setTitle(course.getTitle());
    List<Integer> listgetid = course.getAuthors().stream().map(Author::getId).collect(Collectors.toList());
    courseDTO.setAuthorIds(listgetid);
    courseDTO.setId(course.getId());
    return courseDTO;
  }

  public Course save(CourseDTO courseDTO) {
    if (courseRepository.findByTitle(courseDTO.getTitle()).isPresent()) {
      throw new AppExcepotion(ErrorCode.NOT_FOUND);
    }
    Course course = courseMapper.toCourse(courseDTO);
    if (courseDTO.getAuthorIds() != null) {
      List<Author> authors = authorRepository.findAllById(courseDTO.getAuthorIds());
      course.setAuthors(authors);
    }
    return courseRepository.save(course);
  }

  public List<CourseDTO> getCourse() {
    // return
    // courseRepository.findAll().stream().map(this::toModelCourse).collect(Collectors.toList());
    return courseRepository.findAll().stream().map(this::toModelCourse).collect(Collectors.toUnmodifiableList());
  }

  public Course update(Integer id, CourseDTO courseDTO) {
    Course course = courseRepository.findById(id).orElseThrow(() -> new AppExcepotion(ErrorCode.USER_NOT_FOUND));
    course = courseMapper.updateCourse(course, courseDTO);

    List<Author> author = authorRepository.findAllById(courseDTO.getAuthorIds());
    if (!author.isEmpty()) {
      course.setAuthors(author);
    }

    courseRepository.save(course);

    return course;
  }

  public Course GetByID(Integer id) {
    Course course = courseRepository.findById(id).orElseThrow(() -> new AppExcepotion(ErrorCode.USER_NOT_FOUND));
    return course;
  }

  public boolean DeleteById(Integer id){
        Boolean check = false;
        Course course = courseRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.USER_NOT_FOUND));
        check =true;
        courseRepository.deleteById(id);
        return check;

    }













}
