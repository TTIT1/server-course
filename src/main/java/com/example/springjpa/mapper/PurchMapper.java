package com.example.springjpa.mapper;

import org.mapstruct.Mapper;

import com.example.springjpa.dto.resquest.PurchRequest;
import com.example.springjpa.model.course.Purchase;

@Mapper(componentModel="spring")
public interface  PurchMapper {
    Purchase toPurchase(PurchRequest purchRequest);

}
