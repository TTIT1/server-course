package com.example.springjpa.service;


import com.example.springjpa.dto.resquest.AuthorRequest;
import com.example.springjpa.model.Author;
import com.example.springjpa.model.AuthorCourse;

import java.util.List;


public interface AuthorService {
    AuthorRequest saveAuthor (AuthorRequest authorDTO);
    List<AuthorRequest> getAll();
    Author update(AuthorRequest authorDTO, Integer id);
    List<Author> authorListByNameIngoreCase(String nameFind);
    List<Author> authorListByName(String nameFind);
    Author getAuthorById(Integer id);

}



