package com.example.springjpa.service;

import com.example.springjpa.dto.response.UserResponse;
import com.example.springjpa.dto.resquest.IntrospectrRequest;
import com.example.springjpa.dto.resquest.UserRequest;

public interface UserService {
  Boolean registerNewUserAccount(UserRequest userRequest);

  UserResponse loginUser(UserRequest userRequest);
  // User CheckIdUser(UserDTO userDTO);
  Boolean validateToken(IntrospectrRequest request);
}
