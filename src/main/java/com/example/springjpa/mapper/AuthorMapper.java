package com.example.springjpa.mapper;



import com.example.springjpa.dto.resquest.AuthorRequest;
import com.example.springjpa.model.course.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel ="spring")
public interface AuthorMapper {
     @Mapping(target = "courses", ignore = true)
     Author toAuthor(AuthorRequest authorDTO);
     Author updateAuthor(@MappingTarget Author author, AuthorRequest authorDTO);
   

}
