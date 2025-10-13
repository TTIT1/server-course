package com.example.springjpa.service.Impl;

import com.example.springjpa.dto.response.AuthorCourseResponse;
import com.example.springjpa.dto.resquest.AuthorCourseRequest;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.AuthorCouserMapper;
import com.example.springjpa.model.Author;
import com.example.springjpa.model.AuthorCourse;
import com.example.springjpa.model.AuthorCourseId;
import com.example.springjpa.model.Course;
import com.example.springjpa.repository.AuthorCourseRepository;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;
import com.example.springjpa.service.AuthorCourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class AuthorCourseServiceImpl implements AuthorCourseService {

    CourseRepository courseRepository;
    AuthorRepository authorRepository;
    AuthorCourseRepository authorCourseRepository;

    @Override
    public AuthorCourseResponse add(AuthorCourseRequest request) {
        if (authorRepository.findByEmail(request.getEmail()).isPresent() || courseRepository.findByTitle(request.getTitle()).isPresent()) {
            throw new AppExcepotion(ErrorCode.INVALID_EMAIL);
        }

       if(request.getCourseid()!=null || request.getAuthorid()!=null) {
           Author author = new Author();
           author.setEmail(request.getEmail());
           author.setAge(request.getAge());
           author.setLastName(request.getLastName());
           author.setFirstName(request.getFirstName());
           Course course = new Course();
           course.setTitle(request.getTitle());
           course.setDescription(request.getDescription());

           List<Author> authors = authorRepository.findAllById(Collections.singleton(request.getAuthorid()))
                   .stream()
                   .collect(Collectors.toUnmodifiableList());

           List<Course> courses = courseRepository.findAllById(Collections.singleton(request.getCourseid()))
                   .stream()
                   .collect(Collectors.toUnmodifiableList());

           author.setCourses(courses);
           course.setAuthors(authors);
           authorRepository.save(author);
           courseRepository.save(course);


           // taoj id
           AuthorCourseId authorCourseId = new AuthorCourseId();
           authorCourseId.setAuthorid(author.getId());
           authorCourseId.setCourseid(course.getId());
           // tao bang lei
           AuthorCourse authorCourse = new AuthorCourse();
           authorCourse.setAuthorCourseId(authorCourseId);
           authorCourse.setCourse(course);
           authorCourse.setAuthor(author);
           authorCourse.setDescription(request.getDescription());
           authorCourseRepository.save(authorCourse);
       }        return AuthorCourseResponse.builder()
                .valtile(true)
                .build();
    }
}