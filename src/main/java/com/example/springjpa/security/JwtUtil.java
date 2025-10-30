package com.example.springjpa.security;

import com.example.springjpa.dto.resquest.IntrospectrRequest;


import com.example.springjpa.dto.resquest.RefreshTokenRequest;
import com.example.springjpa.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;

import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.StringJoiner;



//sinh & kiểm tra token.
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class JwtUtil {
  @NonFinal
  @Value("${jwt:select_key}")
  String keyWork;
  @NonFinal
  static Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(keyWork.getBytes());
  }

  @NonFinal
  static long EXPIRATION_TIME = 1000 * 60 * 60;
  @NonFinal
  static long TokenTme = Duration.ofMinutes(2).toMillis();

  // creat token
  public static String generateToken(User user) {
      String id = String.valueOf(user.getId());
      return Jwts.builder()
              .setSubject(user.getUserName())
              .setIssuedAt(new Date())
              .claim("id",user.getId())
              .claim("scope",buildScope(user))
              .setId(id)
              .setIssuer("HANOI UNIVERSITY OF SCIENCE AND TECHNOLOGY")
              .setExpiration(new Date(System.currentTimeMillis()+TokenTme))
              .signWith(SignatureAlgorithm.HS512,key)
              .compact();

  }
    public static String generateRefreshToken(User user) {
        String id = String.valueOf(user.getId());
        return Jwts.builder()
                .setSubject(user.getUserName()+"Kaka")
                .setIssuedAt(new Date())
                .setId(id)
                .setIssuer("HANOI UNIVERSITY OF SCIENCE AND TECHNOLOGY")
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

  public static Boolean validateRefreshToken(RefreshTokenRequest request){
      try {
          String name = extractUsernameToken(request.getRefreshToken());
          if (name !=null&& !isTokenExpiredToken(request.getRefreshToken()) );
          return true;
      }catch (Exception e){
          return  false;
      }
  }

  private static String buildScope(User user){
      StringJoiner stringJoiner = new StringJoiner(" ");
      if(!CollectionUtils.isEmpty(user.getRoles())){
          user.getRoles().forEach(stringJoiner::add);
      }
      return stringJoiner.toString();
  }
    public static String extractUsernameToken(String token) {
        return Jwts. parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject()+"Kaka";
    }
    private static boolean isTokenExpiredToken(String token) {
        Date date = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return date.before(new Date());
    }

}
