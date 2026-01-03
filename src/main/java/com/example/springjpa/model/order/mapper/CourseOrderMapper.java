package com.example.springjpa.model.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.springjpa.model.order.CourseOrder;
import com.example.springjpa.model.order.dto.response.CourseOrderResponse;
import com.example.springjpa.model.order.dto.resquest.CourseOrderRequest;


@Mapper(componentModel ="spring")
public interface CourseOrderMapper {
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "status", ignore = true)
    CourseOrder toCourseOrder(CourseOrderRequest request);
    CourseOrderResponse toCourseOrderResponse(CourseOrder courseOrder);
}

  
