package com.example.springjpa.service.Impl;

import ch.qos.logback.core.spi.ErrorCodes;
import com.example.springjpa.dto.response.IntrospectResponse;
import com.example.springjpa.dto.resquest.IntrospectrRequest;
import com.example.springjpa.security.JwtUtil;


import com.example.springjpa.dto.response.UserResponse;
import com.example.springjpa.dto.resquest.UserRequest;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.UserMapper;
import com.example.springjpa.model.User;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.security.SecurityConfig;
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
        if (userRepository.findBygmail(userRequest.getGmail()).isPresent()){
            throw new AppExcepotion(ErrorCode.INVALID_CREDENTIALS);
        }else {
            PasswordEncoder encoder = new BCryptPasswordEncoder(10);
             User user = new User();
             user.setGmail(userRequest.getGmail());
             user.setPassWordUser(encoder.encode(userRequest.getPassWordUser()));
             userRepository.save(user);
             throw new AppExcepotion(ErrorCode.registerNew_SUCCESS);
        }
    }

    @Override
    public UserResponse loginUser(UserRequest userRequest) {
           User user = userRepository.findBygmail(userRequest.getGmail()).orElseThrow(()->new
                   AppExcepotion( ErrorCode.INVALID_CREDENTIALS));
           PasswordEncoder encoder = new BCryptPasswordEncoder(10);
         Boolean check = encoder.matches(userRequest.getPassWordUser(),user.getPassWordUser());
         if(check){
            String token =   JwtUtil.generateToken(user.getGmail());
               return UserResponse.builder()
                       .Auth(check)
                       .token(token)
                       .build();
        }else {
               throw new AppExcepotion(ErrorCode.INVALID_CREDENTIALS);
           }


    }

    @Override
    public Boolean validateToken(IntrospectrRequest request) {
            Boolean check = JwtUtil.validateToken(request);
            return check;
    }


}