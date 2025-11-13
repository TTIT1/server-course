package com.example.springjpa.service.Impl;

import com.example.springjpa.dto.response.UserResponse;
import com.example.springjpa.dto.resquest.RefreshTokenRequest;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.auth.RefreshToken;
import com.example.springjpa.model.auth.User;
import com.example.springjpa.repository.RefreshTokenRepository;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.security.JwtUtil;
import com.example.springjpa.service.RefreshTokenService;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RefreshTokenServiceImpl implements RefreshTokenService {

    RefreshTokenRepository refreshTokenRepository;
    UserRepository userRepository;

    public UserResponse checkRefreshToken(RefreshTokenRequest request) {

        User user = userRepository.findById(request.getId()).orElseThrow(() -> new AppExcepotion(ErrorCode.FORBIDDEN));

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow(() -> new AppExcepotion(ErrorCode.BAD_REQUEST));

        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest(refreshToken.getRefreshToken(), user.getId());
        Boolean check = JwtUtil.validateRefreshToken(refreshTokenRequest);
         if (!check){
            refreshTokenRepository.deleteById(refreshToken.getId());
             return UserResponse.builder()
                     .refreshToken(refreshToken.getRefreshToken())
                     .token("RefreshToken hết hạn")
                     .Auth(false)
                     .build();
         }
         // tạo refreshToken
            String Refreshnew = JwtUtil.generateRefreshToken(user);

            refreshToken.setRefreshToken(Refreshnew);

            refreshTokenRepository.save(refreshToken);

            String tokennew = JwtUtil.generateToken(user);

            return UserResponse.builder()
                    .refreshToken(refreshToken.getRefreshToken())
                    .token(tokennew)
                    .Auth(true)
                    .build();


    }
}