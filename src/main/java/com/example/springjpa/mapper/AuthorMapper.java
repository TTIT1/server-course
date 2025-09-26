package com.example.springjpa.mapper;


import com.example.springjpa.dto.response.AuthorResponse;
import com.example.springjpa.dto.resquest.AuthorDTO;
import com.example.springjpa.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
     Author toAuthor(AuthorDTO authorDTO);
     Author updateAuthor(@MappingTarget Author author, AuthorDTO authorDTO);

}
