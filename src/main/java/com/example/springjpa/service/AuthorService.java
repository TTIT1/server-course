package com.example.springjpa.service;

import com.example.springjpa.dto.AuthorDTO;
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

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CourseRepository courseRepository;

    public AuthorDTO toModelAuthor(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAge(author.getAge());
        authorDTO.setId(author.getId());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        List<Integer> integers = author.getCourses().stream().map(Course::getId).collect(Collectors.toList());
        authorDTO.setCourseIds(integers);
        return authorDTO;
    }

    public Author saveAuthor(AuthorDTO authorDTO) {
        if (authorRepository.findByEmail(authorDTO.getEmail()).isPresent()) {
             new AppExcepotion(ErrorCode.INVALID_EMAIL);
        }
        Author savedAuthor = new Author();
        savedAuthor.setEmail(authorDTO.getEmail());
        savedAuthor.setFirstName(authorDTO.getFirstName());
        savedAuthor.setLastName(authorDTO.getLastName());
        savedAuthor.setAge(authorDTO.getAge());


        if (authorDTO.getCourseIds() != null) {
            List<Course> courseIds = courseRepository.findAllById(authorDTO.getCourseIds());
            savedAuthor.setCourses(courseIds);

        }
        return authorRepository.save(savedAuthor);

    }

    public List<AuthorDTO> getAll() {
        List<AuthorDTO> authorDTOS = authorRepository.findAll().stream().map(this::toModelAuthor).collect(Collectors.toList());
        if (authorDTOS.isEmpty()){
            throw new AppExcepotion(ErrorCode.NOT_FOUND);
        }
        return authorDTOS;

    }

    public Author update(AuthorDTO authorDTO, Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AppExcepotion(ErrorCode.NOT_FOUND));
        try {

            author.setAge(authorDTO.getAge());
            author.setEmail(authorDTO.getEmail());
            author.setFirstName(authorDTO.getFirstName());
            author.setLastName(authorDTO.getLastName());
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



