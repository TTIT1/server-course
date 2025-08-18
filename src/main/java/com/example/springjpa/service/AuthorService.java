package com.example.springjpa.service;

import com.example.springjpa.config.notification;
import com.example.springjpa.dto.AuthorDTO;
import com.example.springjpa.model.Author;
import com.example.springjpa.model.Course;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
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
            throw new EntityNotFoundException("Author already exists");
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
        return authorDTOS;

    }

    public Author update(AuthorDTO authorDTO, Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new notification("don't data"));
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
            throw new notification("server baoara trì");
        }

    }

    public AuthorDTO getAuthorById(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new notification("Server don't find data to id = " + id));
        return toModelAuthor(author);
    }

    public List<Author> authorListByName(String nameFind) {
        try {
            List<Author> authors = authorRepository.findAllByfirstName(nameFind);
            return authors;
        } catch (RuntimeException e) {
            throw new notification("Server don't find data ");
        }

    }

    public List<Author> authorListByNameIngoreCase(String nameFind) {
        try {
            List<Author> authors = authorRepository.findAllByfirstNameIgnoreCase(nameFind);
            return authors;
        } catch (RuntimeException e) {
            throw new notification("Server don't find data ");
        }
    }
}



