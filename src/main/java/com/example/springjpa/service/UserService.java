package com.example.springjpa.service;

import com.example.springjpa.dto.response.UserResponse;
import com.example.springjpa.dto.response.UserResponseGet;
import com.example.springjpa.dto.resquest.IntrospectrRequest;
import com.example.springjpa.dto.resquest.UserRequest;
import com.example.springjpa.model.User;

import java.util.List;

public interface UserService {
  Boolean registerNewUserAccount(UserRequest userRequest);

  UserResponse loginUser(UserRequest userRequest);
  // User CheckIdUser(UserDTO userDTO);
  Boolean validateToken(IntrospectrRequest request);
  List<UserResponseGet > USER_RESPONSE_GET ();
  UserResponseGet getUser(Long id );

  Boolean updateUser(UserRequest userRequest , Long id );
}
