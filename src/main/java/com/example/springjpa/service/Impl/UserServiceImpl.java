package com.example.springjpa.service.Impl;

import com.example.springjpa.config.SecurityConfig;

import com.example.springjpa.dto.resquest.UserDTO;

import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.UserMapper;
import com.example.springjpa.model.User;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public User registerNewUserAccount(UserDTO userDTO)throws AppExcepotion {
        if (userDTO.getGmail().isEmpty()) {
            throw new AppExcepotion(ErrorCode.INVALID_EMAIL);
        }

        User user = new User();
        user.setPassWordUser(bCryptPasswordEncoder.encode(userDTO.getPassWord()));
        user.setGmail(userDTO.getGmail());
        return userRepository.save(user);
    }

    @Override
    public User loginUser(UserDTO userDTO) {
          User user  = userRepository.findBygmail(userDTO.getGmail()).orElseThrow(()->new AppExcepotion(ErrorCode.INVALID_EMAIL));
     BCryptPasswordEncoder bCryptPasswordEncoder1 = new BCryptPasswordEncoder();
     if(!bCryptPasswordEncoder1.matches(userDTO.getPassWord(),user.getPassWordUser())){

          throw  new AppExcepotion(ErrorCode.SUCCESS);

        }
     return null;

    }



}