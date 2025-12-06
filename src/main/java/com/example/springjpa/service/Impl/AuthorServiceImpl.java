package com.example.springjpa.service.Impl;


import com.example.springjpa.dto.response.AuthorCourseResponse;
import com.example.springjpa.dto.response.AuthorResponse;
import com.example.springjpa.dto.resquest.AuthorCourseRequest;
import com.example.springjpa.dto.resquest.AuthorRequest;
import com.example.springjpa.enums.Roles;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.AuthorMapper;
import com.example.springjpa.model.auth.Role;
import com.example.springjpa.model.auth.User;
import com.example.springjpa.model.course.Author;

import com.example.springjpa.model.course.Course;

import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;

import com.example.springjpa.repository.RoleRepositoty;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.service.AuthorService;

import com.example.springjpa.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class AuthorServiceImpl implements AuthorService  {

   AuthorRepository authorRepository;

   CourseRepository courseRepository;

   AuthorMapper authorMapper;

   UserRepository userRepository;
   RoleRepositoty  roleRepositoty;

BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthorRequest toModelAuthor(Author author) {
        AuthorRequest authorDTO = new AuthorRequest();
        authorDTO.setDob(author.getDob());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        List<String> integers = author.getCourses().stream().map(Course::getId).collect(Collectors.toList());
        authorDTO.setCourseIds(integers);
        return authorDTO;
    }

    public AuthorResponse saveAuthor(AuthorRequest authorDTO) {
        if (authorRepository.findByEmail(authorDTO.getEmail()).isPresent()) {
            new AppExcepotion(ErrorCode.INVALID_EMAIL);
        }


        Author author   =  new Author();
        author.setPassWord(bCryptPasswordEncoder.encode(authorDTO.getPassword()));
        author.setDob(authorDTO.getDob());
        author.setEmail(authorDTO.getEmail());
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        HashSet<String> set = new HashSet<>();
        set.add(Roles.AUTHOR.name());
        author.setRoles(set);
        if (authorDTO.getCourseIds() != null) {
            List<Course> courseIds = courseRepository.findAllById(authorDTO.getCourseIds());
            author.setCourses(courseIds);
        }

        Role role   =roleRepositoty.findByName(Roles.AUTHOR.name());
        User user = new User();
        user.setUserName(authorDTO.getFirstName());
        user.setPasswordUser(bCryptPasswordEncoder.encode(authorDTO.getPassword()));
        user.setGmail(authorDTO.getEmail());
        user.setDob(authorDTO.getDob());
        user.setRoles(Set.of(role));
        userRepository.save(user);
        toModelAuthor(authorRepository.save(author));
        return AuthorResponse.builder()
                .check(true)
                .build();

    }

    public List<AuthorRequest> getAll() {

        List<AuthorRequest> authorDTOS = authorRepository.findAll().stream().map(this::toModelAuthor).collect(Collectors.toUnmodifiableList());

        if (authorDTOS.isEmpty()){
            throw new AppExcepotion(ErrorCode.NOT_FOUND);
        }
        return authorDTOS;

    }

    public AuthorResponse update(AuthorRequest authorDTO, String id) {
           Author author = authorRepository.findById(id).orElseThrow(()-> new AppExcepotion(ErrorCode.NOT_FOUND));

           author = authorMapper.updateAuthor(author,authorDTO);
           authorRepository.save(author);
           return AuthorResponse.builder()
                   .check(true)
                   .build();

    }



    public Author getAuthorById(String id) {
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
        author.setDob(request.getAge());
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
