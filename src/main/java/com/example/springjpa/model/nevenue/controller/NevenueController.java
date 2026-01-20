package com.example.springjpa.model.nevenue.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.nevenue.dto.Request.NevenueRequest;
import com.example.springjpa.model.nevenue.service.NevenueService;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@RequestMapping("/nevenue")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NevenueController {
    NevenueService nevenueService;

    @PostMapping("/add/new/nevenue")
    public ResponseEntity<ApiResponse<Boolean>> saveNevenue( @RequestBody NevenueRequest nevenueRequest){
        
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        apiResponse.setRsulte(nevenueService.addNevenue(nevenueRequest));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        
    }
    
}
