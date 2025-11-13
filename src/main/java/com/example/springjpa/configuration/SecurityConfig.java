package com.example.springjpa.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  // Các endpoint public không yêu cầu token
  private static final String[] PUBLIC_ENDPOINTS = {
      "/swagger-ui/**",
      "/v3/api-docs/**",
      "/swagger-resources/**",
      "/webjars/**",
      "/configuration/**",
      "api/auth/register",
      "api/auth/login",
          "api/auth/send-otp",
          "api/auth/verify-otp",
      "api/auth/check/token",
      "api/auth/check/RefreshToken",
     "api/permission/add/permission",
          "api/role/add/new/role"
  };

  @Value("${select_key}")
  private String key;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http

            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(PUBLIC_ENDPOINTS).permitAll()

                    .anyRequest().authenticated() // Các request khác cần JWT hợp lệ
            )
            .oauth2ResourceServer(ouutho2->ouutho2
                    .jwt(
                    jwtConfigurer -> jwtConfigurer
                            .decoder(jwtDecoder())
                            .jwtAuthenticationConverter(jwtAuthenticationConverter())
                    )
            )
      .csrf(AbstractHttpConfigurer::disable);
    return http.build();
  }

  @Bean
  JwtDecoder jwtDecoder() {
    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA512");
    return NimbusJwtDecoder.withSecretKey(secretKeySpec)
        .macAlgorithm(MacAlgorithm.HS512)
        .build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
    converter.setAuthoritiesClaimName("scope");
    converter.setAuthorityPrefix("ROLE_");
    JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
    jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
    return jwtConverter;
  }

}
