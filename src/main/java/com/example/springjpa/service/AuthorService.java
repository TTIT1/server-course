package com.example.springjpa.service;

import com.example.springjpa.dto.AuthorDTO;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Author;
import com.example.springjpa.model.Course;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public interface AuthorService {
    AuthorDTO saveAuthor (AuthorDTO authorDTO);
    List<AuthorDTO> getAll();
    Author update(AuthorDTO authorDTO, Integer id);
    List<Author> authorListByNameIngoreCase(String nameFind);
    List<Author> authorListByName(String nameFind);
    Author getAuthorById(Integer id);

}



