package com.example.springjpa.model.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.model.order.dto.response.CourseOrderResponse;
import com.example.springjpa.model.order.dto.resquest.CourseOrderRequest;
import com.example.springjpa.model.order.service.CourseOrderService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@RestController
@RequestMapping("/api/course-order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseOrderController {
     CourseOrderService courseOrderService;
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'AUTHOR')")
     @PostMapping("/add")
     public CourseOrderResponse saveCourseOrder( @RequestBody CourseOrderRequest courseOrderRequest){
          return courseOrderService.saveCourseOrder( courseOrderRequest);
     }
   

}
