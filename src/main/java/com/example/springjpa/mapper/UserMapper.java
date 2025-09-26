package com.example.springjpa.mapper;

import com.example.springjpa.dto.UserDTO;
import com.example.springjpa.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface UserMapper {
    User toUserUser(UserDTO user);
}
