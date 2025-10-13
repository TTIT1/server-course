package com.example.springjpa.security;

import com.example.springjpa.dto.resquest.IntrospectrRequest;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;

import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import javax.xml.crypto.Data;

//sinh & kiểm tra token.
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class JwtUtil {
  @NonFinal
  @Value("${jwt.select_key}")
  String keyWork;
  @NonFinal
  static Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(keyWork.getBytes());
  }

  @NonFinal
  static long EXPIRATION_TIME = 1000 * 60 * 60;

  // creat token
  public static String generateToken(String username) {
      return Jwts.builder()
                 .setSubject(username)
                 .setIssuedAt(new Date())
                 .setIssuer("HANOI UNIVERSITY OF SCIENCE AND TECHNOLOGY")
                 .claim("HUST", "ITE6")
                 .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                 .signWith(SignatureAlgorithm.HS512,key)
                 .compact();

  }

  // Lấy username từ token
  public static String extractUsername(String token) {
      return Jwts. parserBuilder()
                  .setSigningKey(key)
                  .build()
                  .parseClaimsJws(token)
                  .getBody()
                  .getSubject();
  }

  // Kiểm tra token có hợp lệ không
  public static Boolean validateToken(IntrospectrRequest request) {
                        try {
                          String username = extractUsername(request.getUsername());
                          if (username.equals(request.getUsername())&& !isTokenExpired(request.getToken()));
                            return true;
                        } catch (Exception e) {
                          return false;
                        }
  }
    

  private static boolean isTokenExpired(String token) {
         Date date = Jwts.parserBuilder()
                         .setSigningKey(key)
                         .build()
                         .parseClaimsJws(token)
                         .getBody()
                         .getExpiration();
            return date.before(new Date());
  }
}
