package com.example.springjpa.service;


import com.example.springjpa.dto.response.AuthorCourseResponse;
import com.example.springjpa.dto.resquest.AuthorCourseRequest;
import com.example.springjpa.dto.resquest.AuthorRequest;
import com.example.springjpa.model.Author;


import java.util.List;


public interface AuthorService {
    AuthorRequest saveAuthor (AuthorRequest authorDTO);
    List<AuthorRequest> getAll();
    Author update(AuthorRequest authorDTO, String id);
    List<Author> authorListByNameIngoreCase(String nameFind);
    List<Author> authorListByName(String nameFind);
    Author getAuthorById(String id);
    AuthorCourseResponse saveAuthorCourse(AuthorCourseRequest request);

}



