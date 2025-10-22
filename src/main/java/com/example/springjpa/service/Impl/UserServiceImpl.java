package com.example.springjpa.service.Impl;

import com.example.springjpa.dto.response.UserResponseGet;
import com.example.springjpa.dto.resquest.IntrospectrRequest;
import com.example.springjpa.enums.Roles;
import com.example.springjpa.model.RefreshToken;
import com.example.springjpa.repository.RefreshTokenRepository;
import com.example.springjpa.security.JwtUtil;


import com.example.springjpa.dto.response.UserResponse;
import com.example.springjpa.dto.resquest.UserRequest;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.UserMapper;
import com.example.springjpa.model.User;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.configuration.SecurityConfig;
import com.example.springjpa.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserServiceImpl implements UserService {
    UserMapper userMapper;
    UserRepository userRepository;
    SecurityConfig securityConfig;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    RefreshTokenRepository refreshTokenRepository;

    public UserResponseGet toUserResponse(User user){
             UserResponseGet userResponseGet =  UserResponseGet
                     .builder()
                     .gmail(user.getGmail())
                     .roles(user.getRoles())
                     .userName(user.getUserName())
                     .build();
             return userResponseGet;
    }

    @Override
    public Boolean registerNewUserAccount(UserRequest userRequest) {
        if(userRepository.findBygmail(userRequest.getGmail()).isPresent()){
            throw  new AppExcepotion(ErrorCode.INVALID_EMAIL);
        }

          User user = new User();
          user.setUserName(userRequest.getUserName());
          HashSet<String> set =new HashSet<>();
          set.add(Roles.USER.name());
          user.setRoles(set);
          user.setPasswordUser(bCryptPasswordEncoder.encode(userRequest.getPassWordUser()));
          user.setGmail(userRequest.getGmail());
          userRepository.save(user);
                             return true;
          }
    @Override
    public UserResponse loginUser(UserRequest userRequest) {
        User user = userRepository.findBygmail(userRequest.getGmail()).orElseThrow(() -> new
                AppExcepotion(ErrorCode.INVALID_CREDENTIALS));

        Boolean check = bCryptPasswordEncoder.matches(userRequest.getPassWordUser(), user.getPasswordUser());
        if (check) {
            String token = JwtUtil.generateToken(user);
            String refreshtoken = JwtUtil.generateRefreshToken(user);
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setRefreshToken(refreshtoken);
            refreshToken.setUser(user);
            refreshTokenRepository.save(refreshToken);
            return UserResponse.builder()
                    .Auth(check)
                    .token(token)
                    .refreshToken(refreshtoken)
                    .build();
        } else {
            throw new AppExcepotion(ErrorCode.INVALID_CREDENTIALS);
        }


    }

    @Override
    public Boolean validateToken(IntrospectrRequest request) {
        Boolean check = JwtUtil.validateToken(request);
        return check;
    }

    @Override
    public List<UserResponseGet> USER_RESPONSE_GET() {
          List <UserResponseGet> userResponseGets = userRepository.findAll()
                   .stream()
                  .map(this::toUserResponse).collect(Collectors.toUnmodifiableList());
          return userResponseGets;
    }

    @Override
    public UserResponseGet getUser(Long id) {
        User userResponseGet = userRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.BAD_REQUEST));

        return new UserResponseGet(userResponseGet.getGmail(), userResponseGet.getUserName(), userResponseGet.getRoles());
    }




    public Boolean updateUser(UserRequest userRequest , Long id){
        User user   = userRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.DUPLICATE_RECORD));
             user.setUserName(user.getUserName());
             user.setPasswordUser(user.getPasswordUser());
             user.setGmail(userRequest.getGmail());
             userRepository.save(user);
             return true;
    }

}

