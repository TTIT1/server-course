package com.example.springjpa.service;

import com.example.springjpa.dto.response.UserResponse;
import com.example.springjpa.dto.resquest.RefreshTokenRequest;

public interface RefreshTokenService {
            UserResponse checkRefreshToken(RefreshTokenRequest request);

}
