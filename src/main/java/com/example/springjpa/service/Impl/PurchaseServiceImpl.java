package com.example.springjpa.service.Impl;

import com.example.springjpa.model.auth.User;
import com.example.springjpa.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.springjpa.dto.response.PurchResponse;
import com.example.springjpa.dto.resquest.PurchRequest;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.PurchMapper;
import com.example.springjpa.model.course.Course;
import com.example.springjpa.model.course.Purchase;
import com.example.springjpa.repository.CourseRepository;
import com.example.springjpa.repository.PurchaseRepository;
import com.example.springjpa.repository.UserRepository;
import com.example.springjpa.service.PurchServer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;



@Slf4j
@Service
@FieldDefaults(level =  AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
public class PurchaseServiceImpl implements  PurchServer {

    PurchMapper purchMapper;
    PurchaseRepository purchaseRepository;
    UserRepository userRepository;
    CourseRepository courseRepository;
    JwtUtil  jwtUtil;
    @Override
    public PurchResponse add(PurchRequest purchRequest) {
        JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();


        Purchase purchase = purchaseRepository.findByUserIdAndCourseId(purchRequest.getId_user(),purchRequest.getId_course());
        // kieerm tra xem user id có chung vs id nguowiuf mua không tròn token
      if(purchase == null &&  purchRequest.getId_user().equals(authentication.getToken().getClaim("id"))) {

                 User user = userRepository.findById(purchRequest.getId_user()).orElseThrow(()->new AppExcepotion(ErrorCode.USER_NOT_FOUND));

                 Course course = courseRepository.findById(purchRequest.getId_course()).orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));
                 purchase = new Purchase(user,course,purchRequest.getPurchasedAt());
              purchaseRepository.save(purchase);

                 return PurchResponse.builder()
                         .valid(true)
                         .build();

      }
      else {
          throw new AppExcepotion(ErrorCode.COURSE_ALREADY_PURCHASED);
      }
    }

    
       
}
