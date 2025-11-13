package com.example.springjpa.mapper;


import com.example.springjpa.dto.response.UserResponse;
import com.example.springjpa.dto.response.UserResponseGet;
import com.example.springjpa.dto.resquest.UserRequest;
import com.example.springjpa.model.auth.User;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface UserMapper {

    UserResponseGet toUserUser (User user);
}
