package com.example.springjpa.service;

import com.example.springjpa.dto.AuthorDTO;
import com.example.springjpa.model.Author;

import java.util.List;


public interface AuthorService {
    AuthorDTO saveAuthor (AuthorDTO authorDTO);
    List<AuthorDTO> getAll();
    Author update(AuthorDTO authorDTO, Integer id);
    List<Author> authorListByNameIngoreCase(String nameFind);
    List<Author> authorListByName(String nameFind);
    Author getAuthorById(Integer id);

}



