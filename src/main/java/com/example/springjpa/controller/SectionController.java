package com.example.springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springjpa.dto.ApiResponse;
import com.example.springjpa.dto.SectionDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Section;
import com.example.springjpa.service.SectionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@@RequestMapping("/api/v3")
public class SectionController {
    @Autowired
    SectionService sectionService;

    @PostMapping("/add/new/section")
    public ResponseEntity<ApiResponse<Section>> AddSection(@RequestBody SectionDTO sectionDTO){
         ApiResponse<Section> apiResponse = new ApiResponse<>();
         apiResponse.setRsulte(sectionService.add(sectionDTO));
          apiResponse.setCode(ErrorCode.SUCCESS.getCode());
                    apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
                    return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
