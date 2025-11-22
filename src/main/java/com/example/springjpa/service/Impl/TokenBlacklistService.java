package com.example.springjpa.service.Impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor()

public class TokenBlacklistService {

     RedisTemplate<String, Object> redisTemplate;

    private static final String PREFIX = "blacklist:";

    public void blacklistToken(String tokenId, long durationInSeconds) {
        String key = PREFIX + tokenId;


        redisTemplate.opsForValue().set(key, "revoked", durationInSeconds, TimeUnit.SECONDS);
    }
  // kiem tra xem token còn bị cấm không
    public boolean isTokenBlacklisted(String tokenId) {
        String key = PREFIX + tokenId;
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }


}
