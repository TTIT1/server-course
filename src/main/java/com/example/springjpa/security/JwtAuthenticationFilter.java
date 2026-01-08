package com.example.springjpa.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.springjpa.service.Impl.TokenBlacklistService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


     JwtUtil jwtUtil;


     TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");

           String jti = null;
            try {
                jti = jwtUtil.extractJti(token);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token format");
                return;
            }

            if (jti != null && tokenBlacklistService.isTokenBlacklisted(jti)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token has been revoked");
                return;
            }



        }

        filterChain.doFilter(request, response);
    }
    
 
  public  String getIdByUsertoToken(){
     HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
        .getRequest();
        String authHeader = request.getHeader("Authorization");
          String token = authHeader.substring(7);
        return  JwtUtil .valiuserIDToken(token);
  
  }
}
