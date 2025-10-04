package com.example.springjpa.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
//sinh & kiểm tra token.
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class JwtUtil {

     @NonFinal
     static String SECRET_KEY = "mySecretKeyMySecretKeyMySecretKey123";
     @NonFinal
     static  long EXPIRATION_TIME = 1000 * 60 * 60;
     @NonFinal
     static Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


    // Sinh token muốn trả về trong token đó có gì thì mình sẽ sét trong nay
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setIssuer("HANOI UNIVERSITY OF SCIENCE AND TECHNOLOGY")
                .claim("TTITE6","HUST")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    // Lấy username từ token
    public static String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();// get id or usernae
    }
    // Kiểm tra token có hợp lệ không
    public static boolean validateToken(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (JwtException e) {
            return false;
        }
    }

    private static boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();// get time
        return expiration.before(new Date());// check time
    }
}