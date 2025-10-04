package com.example.springjpa.service.Impl;

import com.example.springjpa.dto.response.IntrospectResponse;
import com.example.springjpa.dto.resquest.IntrospectrRequest;
import com.example.springjpa.security.JwtUtil;
import com.example.springjpa.security.SecurityConfig;


import com.example.springjpa.dto.response.UserResponse;
import com.example.springjpa.dto.resquest.UserRequest;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.UserMapper;
import com.example.springjpa.model.User;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserServiceImpl implements UserService {
    UserMapper userMapper;
    UserRepository userRepository;
    SecurityConfig securityConfig;
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Boolean registerNewUserAccount(UserRequest userRequest) {
         if(userRepository.findBygmail(userRequest.getGmail()).isPresent()){
             throw new AppExcepotion(ErrorCode.INVALID_EMAIL);
         }else {
             PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
             User user = new User();
             user.setPassWordUser(passwordEncoder.encode(userRequest.getPassWordUser()));
             user.setGmail(userRequest.getGmail());
             userRepository.save(user);
             return true;
         }
    }

    @Override
    public UserResponse loginUser(UserRequest userRequest) {
               User user = userRepository.findBygmail(userRequest.getGmail())
                       .orElseThrow(()->new AppExcepotion(ErrorCode.INVALID_CREDENTIALS));
               PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
               Boolean check = passwordEncoder.matches(userRequest.getPassWordUser(), user.getPassWordUser());
               if (!check){
                   throw  new AppExcepotion(ErrorCode.INVALID_CREDENTIALS);
               }
               String token = JwtUtil .generateToken(userRequest.getGmail());
                return  UserResponse.builder()
                        .Auth(check)
                        .token(token)
                        .build();
    }

    @Override
    public Boolean validateToken(String token, String username) {
               Boolean check = JwtUtil.validateToken(token,username);
               return check;
    }

}