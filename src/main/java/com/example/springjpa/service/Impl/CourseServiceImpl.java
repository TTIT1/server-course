package com.example.springjpa.service.Impl;

import com.example.springjpa.dto.resquest.*;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.CourseMapper;
import com.example.springjpa.model.course.*;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.PurchaseRepository;
import com.example.springjpa.service.CourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseServiceImpl implements CourseService {
  CourseMapper courseMapper;
  CourseRepository courseRepository;
  AuthorRepository authorRepository;
  PurchaseRepository purchaseRepository;
  LectureRepository lectureRepository;

  public CourseDTO toModelCourse(Course course) {
    CourseDTO courseDTO = new CourseDTO();
    courseDTO.setDescription(course.getDescription());
    courseDTO.setTitle(course.getTitle());
    List<String> listgetid = course.getAuthors().stream().map(Author::getId).collect(Collectors.toList());
    courseDTO.setAuthorIds(listgetid);
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

    @Override

    public CourseDTO getFullCourse(CouresUserRequest req) {

        Boolean purchased = purchaseRepository.hasPurchase(
                req.getUserId(), req.getCourseId()
        );

        if (!purchased) {
            throw new AppExcepotion(ErrorCode.COURSE_NOT_PURCHASED);
        }

        Course course = courseRepository.findByCourseId(req.getCourseId());

        CourseDTO courseDTO = courseMapper.toDto(course);
        courseDTO.setSections(courseMapper.toSectionDtos(course.getSections()));
        Set<LectureDTO> allLectures = course.getSections()
                .stream()
                .flatMap(section -> section.getLecture().stream())
                .map(courseMapper::toLectureDto).collect(Collectors.toSet());
               courseDTO.setLectures(allLectures);

      return courseDTO;
    }




    public List<CourseDTO> getCourse() {
    // return
    // courseRepository.findAll().stream().map(this::toModelCourse).collect(Collectors.toList());
    return courseRepository.findAll().stream().map(this::toModelCourse).collect(Collectors.toUnmodifiableList());
  }

  public Course update(String id, CourseDTO courseDTO) {
    Course course = courseRepository.findById(id).orElseThrow(() -> new AppExcepotion(ErrorCode.USER_NOT_FOUND));
    course = courseMapper.updateCourse(course, courseDTO);

    List<Author> author = authorRepository.findAllById(courseDTO.getAuthorIds());
    if (!author.isEmpty()) {
      course.setAuthors(author);
    }

    courseRepository.save(course);

    return course;
  }

  public Course GetByID(String id) {
    Course course = courseRepository.findById(id).orElseThrow(() -> new AppExcepotion(ErrorCode.USER_NOT_FOUND));
    return course;
  }

  public boolean DeleteById(String id){
        Boolean check = false;
        Course course = courseRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.USER_NOT_FOUND));
        check =true;
        courseRepository.deleteById(id);
        return check;

    }













}
