package com.example.springjpa.service.Impl;


import com.example.springjpa.dto.response.AuthorCourseResponse;
import com.example.springjpa.dto.resquest.AuthorCourseRequest;
import com.example.springjpa.dto.resquest.AuthorRequest;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.AuthorMapper;
import com.example.springjpa.model.Author;

import com.example.springjpa.model.Course;

import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;
import com.example.springjpa.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class AuthorServiceImpl implements AuthorService  {

   AuthorRepository authorRepository;

   CourseRepository courseRepository;

   AuthorMapper authorMapper;


    public AuthorRequest toModelAuthor(Author author) {
        AuthorRequest authorDTO = new AuthorRequest();
        authorDTO.setAge(author.getAge());
        authorDTO.setId(author.getId());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        List<Integer> integers = author.getCourses().stream().map(Course::getId).collect(Collectors.toList());
        authorDTO.setCourseIds(integers);
        return authorDTO;
    }

    public AuthorRequest saveAuthor(AuthorRequest authorDTO) {
        if (authorRepository.findByEmail(authorDTO.getEmail()).isPresent()) {
            new AppExcepotion(ErrorCode.INVALID_EMAIL);
        }
         Author author = authorMapper.toAuthor(authorDTO);
        if (authorDTO.getCourseIds() != null) {
            List<Course> courseIds = courseRepository.findAllById(authorDTO.getCourseIds());
            author.setCourses(courseIds);
        }
       return toModelAuthor(authorRepository.save(author));

    }

    public List<AuthorRequest> getAll() {

        List<AuthorRequest> authorDTOS = authorRepository.findAll().stream().map(this::toModelAuthor).collect(Collectors.toUnmodifiableList());

        if (authorDTOS.isEmpty()){
            throw new AppExcepotion(ErrorCode.NOT_FOUND);
        }
        return authorDTOS;

    }

    public Author update(AuthorRequest authorDTO, Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AppExcepotion(ErrorCode.NOT_FOUND));
        try {
               author = authorMapper.updateAuthor(author,authorDTO);
            List<Course> courses = courseRepository.findAllById(authorDTO.getCourseIds());
            if (courses != null) {
                author.setCourses(courses);
            }
            return authorRepository.save(author);


        } catch (RuntimeException e) {
            throw new AppExcepotion(ErrorCode.INVALID_INPUT);
        }

    }



    public Author getAuthorById(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AppExcepotion(ErrorCode.NOT_FOUND));
        return author;
    }

    @Override
    public AuthorCourseResponse saveAuthorCourse(AuthorCourseRequest request) {
        if (authorRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new AppExcepotion(ErrorCode.INVALID_EMAIL);
        }

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course = courseRepository.save(course);

        Author author = new Author();
        author.setEmail(request.getEmail());
        author.setAge(request.getAge());
        author.setLastName(request.getLastName());
        author.setFirstName(request.getFirstName());
        author = authorRepository.save(author);

        author.getCourses().add(course);
        course.getAuthors().add(author);

        courseRepository.save(course);

           return AuthorCourseResponse.builder()
                   .valtile(true)
                   .build();
    }

    public List<Author> authorListByName(String nameFind) {
        try {
            List<Author> authors = authorRepository.findAllByfirstName(nameFind);
            return authors;
        } catch (RuntimeException e) {
            throw new AppExcepotion(ErrorCode.INVALID_INPUT);
        }

    }

    public List<Author> authorListByNameIngoreCase(String nameFind) {
        try {
            List<Author> authors = authorRepository.findAllByfirstNameIgnoreCase(nameFind);
            if (authors.isEmpty()){
                throw new AppExcepotion(ErrorCode.NOT_FOUND);
            }
            return authors;
        } catch (RuntimeException e) {
            throw new AppExcepotion(ErrorCode.INVALID_INPUT);
        }
    }
}
