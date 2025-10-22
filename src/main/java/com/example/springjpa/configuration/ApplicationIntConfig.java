package com.example.springjpa.configuration;

import com.example.springjpa.enums.Roles;
import com.example.springjpa.model.User;
import com.example.springjpa.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ApplicationIntConfig {
    PasswordEncoder passwordEncoder;

     @Bean
    ApplicationRunner applicationRunner (UserRepository userRepository){
         return args ->{
                 if(userRepository.findByUserName("admin").isEmpty()){
                          var roles = new HashSet<String>();
                          roles.add(Roles.ADMIN.name());
                     User user  = User.builder()
                             .userName("admin")
                             .roles(roles)
                             .passwordUser(passwordEncoder.encode("admin123"))
                             .gmail("admin@gmail.com")
                             .build();
                     userRepository.save(user);
                     log.warn("creat user admin "+user);
                 }

         };
     }


}
