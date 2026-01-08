package com.example.springjpa.model.nevenue.mapper;

import org.mapstruct.Mapper;

import com.example.springjpa.model.nevenue.dto.Request.NevenueRequest;
import com.example.springjpa.model.nevenue.model.Nevenue;

@Mapper(componentModel ="spring")
public interface  NevenueMapper {
    Nevenue toNevenue(NevenueRequest nevenueRequest);
}
