package com.example.springjpa.service;

import com.example.springjpa.dto.UserDTO;
import com.example.springjpa.dto.resquest.UserResquest;
import com.example.springjpa.model.User;

public interface UserService {
   User registerNewUserAccount(UserDTO userDTO);
   User loginUser(UserDTO userDTO);
   //User CheckIdUser(UserDTO userDTO);
}
