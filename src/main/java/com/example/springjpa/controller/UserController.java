package com.example.springjpa.controller;


import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.response.IntrospectResponse;
import com.example.springjpa.dto.response.UserResponse;

import com.example.springjpa.dto.resquest.IntrospectrRequest;
import com.example.springjpa.dto.resquest.UserRequest;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.mbeans.SparseUserDatabaseMBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerNewUserAccount(@RequestBody UserRequest userRequest){
                    Boolean resulte = userService.registerNewUserAccount(userRequest);
                    ApiResponse apiResponse = new ApiResponse();
                    apiResponse.setRsulte(resulte);
                    apiResponse.setCode(ErrorCode.SUCCESS.getCode());
                    apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
                    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
     }
    @PostMapping("/login")
    public ApiResponse<UserResponse> login(@RequestBody UserRequest userRequest){
         UserResponse userResponse = userService.loginUser(userRequest);
         return ApiResponse.<UserResponse> builder()
                 .rsulte(UserResponse.builder()
                         .token(userResponse.getToken())
                         .Auth(userResponse.isAuth())
                         .build())
                 .build();
     }
     @PostMapping("/check/token")
    public ApiResponse<IntrospectResponse> apiResponse(@RequestBody IntrospectrRequest request){
            Boolean check = userService.validateToken(request);
            return ApiResponse.<IntrospectResponse>builder()
                    .rsulte(IntrospectResponse.builder()
                            .valid(check)
                            .build())
                    .build();
     }

}
