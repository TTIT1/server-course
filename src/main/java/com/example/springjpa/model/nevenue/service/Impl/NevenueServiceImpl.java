package com.example.springjpa.model.nevenue.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.course.Author;
import com.example.springjpa.model.course.Course;
import com.example.springjpa.model.nevenue.dto.Request.NevenueRequest;
import com.example.springjpa.model.nevenue.mapper.NevenueMapper;
import com.example.springjpa.model.nevenue.model.Nevenue;
import com.example.springjpa.model.nevenue.repository.NevenueRepository;
import com.example.springjpa.model.nevenue.service.NevenueService;
import com.example.springjpa.model.order.CourseOrder;
import com.example.springjpa.model.order.CourseOrderRepository;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;
import com.example.springjpa.security.JwtAuthenticationFilter;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Service
@RequiredArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE,makeFinal=true)
public class NevenueServiceImpl implements NevenueService {
     NevenueRepository nevenueRepository;
     NevenueMapper nevenueMapper;
     CourseOrderRepository courseOrderRepository;
     JwtAuthenticationFilter  authenticationFilter;
     AuthorRepository authorRepository;
     CourseRepository   courseRepository;
    @Override
    
    public Boolean addNevenue(NevenueRequest nevenueRequest) {
            
      String userId = authenticationFilter.getIdByUsertoToken();
           
          if(!courseOrderRepository.existsByUserIdAndCourseId(userId, nevenueRequest.getCourseId())){
                throw new AppExcepotion(ErrorCode.COURSE_NOT_PURCHASED);    
          }
              Course course = courseRepository
            .findByCourseId(nevenueRequest.getCourseId());

    CourseOrder courseOrder =
            courseOrderRepository.findByUserIdAndCourseId(
                userId, nevenueRequest.getCourseId());

    List<Author> authors =
            authorRepository.findAuthorsByCourseId(nevenueRequest.getCourseId());

    for (Author author : authors) {
        Nevenue n = Nevenue.builder()
            .course(course)          
            .author(author)          
            .courseOrder(courseOrder)
            .build();

        nevenueRepository.save(n);
    }



        return true;
        }
    }



    

