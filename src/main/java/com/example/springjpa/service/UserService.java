package com.example.springjpa.service;

import com.example.springjpa.dto.response.UserResponse;
import com.example.springjpa.dto.response.UserResponseGet;
import com.example.springjpa.dto.resquest.IntrospectrRequest;
import com.example.springjpa.dto.resquest.UserRequest;
import com.example.springjpa.dto.resquest.UserRequestregister;
import com.example.springjpa.dto.resquest.UserResetPasswordRequest;


import java.util.List;

public interface UserService {
  Boolean registerNewUserAccount(UserRequestregister userRequest);

  UserResponse loginUser(UserRequest userRequest);
  // User CheckIdUser(UserDTO userDTO);
  Boolean validateToken(IntrospectrRequest request);
  List<UserResponseGet > USER_RESPONSE_GET ();
  UserResponseGet getUser(String id );
  UserResponseGet getInfo();
  Boolean updateUser(UserRequest userRequest , String id );
  Boolean forgotPassword(UserResetPasswordRequest userResetPasswordRequest);

}
