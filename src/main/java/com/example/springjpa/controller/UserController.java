package com.example.springjpa.controller;

import com.example.springjpa.dto.UserDTO;
import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.resquest.UserResquest;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.User;
import com.example.springjpa.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserService userService;
    @PostMapping("/creat/new/user")
    public ResponseEntity<ApiResponse> apiResponseRequestEntity (@RequestBody UserDTO userDTO){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(userService.registerNewUserAccount(userDTO));
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @GetMapping("/login")
    public ResponseEntity<ApiResponse> apiResponseResponseEntity (@RequestBody UserDTO userDTO){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(userService.loginUser(userDTO));
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
