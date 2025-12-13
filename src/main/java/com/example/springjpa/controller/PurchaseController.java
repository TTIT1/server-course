package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.response.PurchResponse;
import com.example.springjpa.dto.resquest.PurchRequest;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.repository.PurchaseRepository;
import com.example.springjpa.service.Impl.PurchaseServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class PurchaseController {
    PurchaseServiceImpl purchaseService;
    PurchaseRepository purchaseRepository;
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add/new/purchase")
    public ApiResponse<PurchResponse> createPurchase(@RequestBody PurchRequest purchRequest){
             PurchResponse purchResponse = purchaseService.add(purchRequest);
           return ApiResponse.<PurchResponse>builder()
                   .rsulte(purchResponse)
                   .code(ErrorCode.SUCCESS.getCode())
                   .messages(ErrorCode.SUCCESS.getMessage())
                   .httpStatusCode(ErrorCode.SUCCESS.getHttpStatusCode())
                   .build();
    }

}
