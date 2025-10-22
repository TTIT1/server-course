package com.example.springjpa.controller;


import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.response.IntrospectResponse;
import com.example.springjpa.dto.response.UserResponse;

import com.example.springjpa.dto.response.UserResponseGet;
import com.example.springjpa.dto.resquest.IntrospectrRequest;
import com.example.springjpa.dto.resquest.RefreshTokenRequest;
import com.example.springjpa.dto.resquest.UserRequest;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.service.RefreshTokenService;
import com.example.springjpa.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserService userService;
    RefreshTokenService refreshTokenService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerNewUserAccount(@RequestBody UserRequest userRequest){
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
                         .refreshToken(userResponse.getRefreshToken())
                         .build())
                 .build();
     }
     @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
     @PostMapping("/check/token")
    public ApiResponse<IntrospectResponse> apiResponse(@RequestBody IntrospectrRequest request){
            Boolean check = userService.validateToken(request);
            return ApiResponse.<IntrospectResponse>builder()
                    .rsulte(IntrospectResponse.builder()
                            .valid(check)
                            .build())
                    .build();
     }
     @PreAuthorize("hasRole('ADMIN')")
     @GetMapping("/get/all/user")
    public ApiResponse<List<UserResponseGet>> apiResponse (){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("username "+authentication.getName());
        log.warn("rolse "+authentication.getAuthorities());
               List<UserResponseGet> userResponseGets = userService.USER_RESPONSE_GET();
               return ApiResponse.<List<UserResponseGet>>builder()
                       .rsulte(userResponseGets)
                       .code(ErrorCode.SUCCESS.getCode())
                       .messages(ErrorCode.SUCCESS.getMessages())
                       .build();
     }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.claims['id']")
    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseGet> userResponseGetResponseEntity(@PathVariable Long id) {
        UserResponseGet userResponseGet = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseGet);
    }
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.claims['id']")
   @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> booleanResponseEntity(@RequestBody UserRequest userRequest, @PathVariable Long id)
   {
       Boolean check = userService.updateUser(userRequest,id);

       return ResponseEntity.status(HttpStatus.OK).body(check);

   }
   @PostMapping("/check/RefreshToken")
   public ResponseEntity<UserResponse> booleanResponseEntity(@RequestBody RefreshTokenRequest request){
                     UserResponse userResponse = refreshTokenService.checkRefreshToken(request);
                     return ResponseEntity.status(HttpStatus.OK).body(userResponse);
   }


}
