package com.example.springjpa.service.Impl;

import com.example.springjpa.dto.response.UserResponseGet;
import com.example.springjpa.dto.resquest.IntrospectrRequest;
import com.example.springjpa.dto.resquest.UserRequestregister;
import com.example.springjpa.dto.resquest.UserResetPasswordRequest;
import com.example.springjpa.enums.Roles;
import com.example.springjpa.model.auth.RefreshToken;
import com.example.springjpa.model.auth.Role;
import com.example.springjpa.repository.RefreshTokenRepository;
import com.example.springjpa.repository.RoleRepositoty;
import com.example.springjpa.security.JwtUtil;


import com.example.springjpa.dto.response.UserResponse;
import com.example.springjpa.dto.resquest.UserRequest;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.UserMapper;
import com.example.springjpa.model.auth.User;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.configuration.SecurityConfig;
import com.example.springjpa.service.EmailService;
import com.example.springjpa.service.OptService;
import com.example.springjpa.service.UserService;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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
    RoleRepositoty  roleRepositoty;
    OptService optService;
    EmailService emailService;
    JwtUtil jwtUtil;

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
    public Boolean registerNewUserAccount(UserRequestregister userRequest) {
        if(userRepository.findBygmail(userRequest.getGmail()).isPresent()){
            throw  new AppExcepotion(ErrorCode.INVALID_EMAIL);
        }
        Role roleUser = roleRepositoty.findByName(Roles.USER.name());

         User user = new User();
         user.setUserName(userRequest.getUserName());
         user.setDob(LocalDate.of(userRequest.getBirthDate().getYear(),userRequest.getBirthDate().getMonth(),userRequest.getBirthDate().getDayOfMonth()));
         user.setRoles(Set.of(roleUser));
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
    public UserResponseGet getUser(String id) {
        User userResponseGet = userRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.BAD_REQUEST));

        return new UserResponseGet(userResponseGet.getGmail(), userResponseGet.getUserName(), userResponseGet.getRoles());
    }

    @Override
    public UserResponseGet getInfo() {
       var context = SecurityContextHolder.getContext();
         String name = context.getAuthentication().getName();
         String role =  context.getAuthentication().getAuthorities().iterator().next().getAuthority();
         User user = userRepository.findByUserName(name).orElseThrow(
                 ()->new AppExcepotion(ErrorCode.USER_NOT_FOUND));
         return userMapper.toUserUser(user);
    }


    public Boolean updateUser(UserRequest userRequest , String id){
        User user   = userRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.DUPLICATE_RECORD));
             user.setUserName(user.getUserName());
             user.setPasswordUser(bCryptPasswordEncoder.encode(userRequest.getPassWordUser()));
             user.setGmail(userRequest.getGmail());
             userRepository.save(user);
             return true;
    }

    @Override
    public Boolean forgotPassword(UserResetPasswordRequest  userRequest) {
        User user   = userRepository.findBygmail(userRequest.getEmail()).orElseThrow(()->new AppExcepotion(ErrorCode.USER_NOT_FOUND));
        user.setPasswordUser(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        return true;

    }


}

