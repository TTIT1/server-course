package com.example.springjpa.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.response.IntrospectResponse;
import com.example.springjpa.dto.response.UserRegisterResponse;
import com.example.springjpa.dto.response.UserResponse;
import com.example.springjpa.dto.response.UserResponseGet;
import com.example.springjpa.dto.resquest.IntrospectrRequest;
import com.example.springjpa.dto.resquest.RefreshTokenRequest;
import com.example.springjpa.dto.resquest.UserRequest;
import com.example.springjpa.dto.resquest.UserRequestregister;
import com.example.springjpa.dto.resquest.UserResetPasswordRequest;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.security.JwtUtil;
import com.example.springjpa.service.EmailService;
import com.example.springjpa.service.Impl.TokenBlacklistService;
import com.example.springjpa.service.OptService;
import com.example.springjpa.service.RefreshTokenService;
import com.example.springjpa.service.UserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    OptService otpService;
    EmailService emailService;
    UserService userService;
    RefreshTokenService refreshTokenService;
    JwtUtil  jwtUtil;
    TokenBlacklistService  tokenBlacklistService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserRegisterResponse>> registerNewUserAccount(@Valid@RequestBody UserRequestregister userRequest){
                    Boolean resulte = userService.registerNewUserAccount(userRequest);
                    ApiResponse apiResponse = new ApiResponse();
                    apiResponse.setRsulte(resulte);
                    apiResponse.setCode(ErrorCode.SUCCESS.getCode());
                    apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
                    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
     }
    @PostMapping("/logout")
    public ResponseEntity<UserRegisterResponse> logout(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        log.info(jwt);
        String jti = jwtUtil.extractJti(jwt);
        long expiration = jwtUtil.getExpirationRemaining(jwt);

        tokenBlacklistService.blacklistToken(jti, expiration);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setValid(true);
        return ResponseEntity.status(HttpStatus.OK).body(userRegisterResponse);
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
     @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'AUTHOR')")
     @PostMapping("/check/token")
    public ApiResponse<IntrospectResponse> apiResponse(@RequestBody IntrospectrRequest request){
            Boolean check = userService.validateToken(request);
            return ApiResponse.<IntrospectResponse>builder()
                    .rsulte(IntrospectResponse.builder()
                            .valid(check)
                            .build())
                    .build();
     }
    @PreAuthorize("hasAuthority('user.view') or hasRole('ADMIN')")
    @GetMapping("/get/all/user")
    public ApiResponse<List<UserResponseGet>> apiResponse (){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
               List<UserResponseGet> userResponseGets = userService.USER_RESPONSE_GET();
               return ApiResponse.<List<UserResponseGet>>builder()
                       .rsulte(userResponseGets)
                       .code(ErrorCode.SUCCESS.getCode())
                       .messages(ErrorCode.SUCCESS.getMessage())
                       .build();
     }


    @PreAuthorize("hasAnyAuthority('user.view','profile.view') or hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseGet> userResponseGetResponseEntity(@PathVariable String id) {
        UserResponseGet userResponseGet = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseGet);
    }
    @GetMapping("/info")
    public ResponseEntity<UserResponseGet> getInfo() {
        UserResponseGet userResponseGet = userService.getInfo();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseGet);
    }

    @PreAuthorize("hasAnyAuthority('user.update','profile.update') and #id == authentication.principal.id")
   @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> booleanResponseEntity(@RequestBody UserRequest userRequest, @PathVariable String id)
   {
       Boolean check = userService.updateUser(userRequest,id);

       return ResponseEntity.status(HttpStatus.OK).body(check);

   }
   @PostMapping("/check/RefreshToken")
   public ResponseEntity<UserResponse> booleanResponseEntity(@RequestBody RefreshTokenRequest request){
                     UserResponse userResponse = refreshTokenService.checkRefreshToken(request);
                     return ResponseEntity.status(HttpStatus.OK).body(userResponse);
   }
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestParam String email) {

        String otp = otpService.generateOTP(email,10);
        try {
            emailService.sendOtpEmail(email, otp);
            return ResponseEntity.ok("Đã gửi OTP tới email.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Gửi mail thất bại: " + e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean ok = otpService.verifyOTP(email, otp);
        if (ok) {
            return ResponseEntity.ok("Xác thực thành công.");
        } else {
            return ResponseEntity.status(400).body("OTP không hợp lệ hoặc đã hết hạn.");
        }
    }
   @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody UserResetPasswordRequest userResetPasswordRequest) {
        Boolean ok = userService.forgotPassword(userResetPasswordRequest);
       if (ok) {
           return ResponseEntity.ok(ok);
       } else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ok);
       }
   }




}
