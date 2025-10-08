package com.example.springjpa.security;

import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
 private String [] Public_Inpoint = {"/swagger-ui/**",
         "/v3/api-docs/**",
         "/swagger-resources/**",
         "/webjars/**",
         "/configuration/**",
       "/api/auth/**"
 };
    @Value("${jwt.select_key}")
   private   String key ;
    @Autowired
    private SwaggerConfig swaggerConfig;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http.authorizeHttpRequests(request->
               request.requestMatchers(HttpMethod.POST,Public_Inpoint).permitAll()
                       .requestMatchers(HttpMethod.GET,Public_Inpoint).permitAll()
                       //everyone request check token
                       .anyRequest().authenticated()
       );
       // check token for request
       http.oauth2ResourceServer(oauth2->
               oauth2.jwt((jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))));

                  http.csrf(AbstractHttpConfigurer::disable);
       return http.build();

    }
    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),"HS512");
       return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512)
               .build();
    };

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
