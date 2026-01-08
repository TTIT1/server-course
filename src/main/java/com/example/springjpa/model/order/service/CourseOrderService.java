package com.example.springjpa.model.order.service;

import com.example.springjpa.dto.response.CouresAllResponse.CourseDetailResponse;
import com.example.springjpa.model.order.dto.response.CourseOrderResponse;
import com.example.springjpa.model.order.dto.resquest.CourseOrderRequest;

public interface CourseOrderService {
   
        CourseOrderResponse saveCourseOrder(CourseOrderRequest courseOrder);

     
     
        CourseDetailResponse getPurchasedCourseDetail(String courseId);

}
