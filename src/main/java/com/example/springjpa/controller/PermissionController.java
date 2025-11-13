package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.response.PermisstionResponse;
import com.example.springjpa.dto.resquest.PermissionRequest;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.service.PermisstionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PermissionController {
    PermisstionService permisstionService;

    @PostMapping("/add/permission")
    public ApiResponse<PermisstionResponse> creatPermission(@RequestBody PermissionRequest request){
              PermisstionResponse permisstionResponse = permisstionService.creatPermisstionResponse(request);
              return ApiResponse.<PermisstionResponse>builder()
                      .rsulte(permisstionResponse)
                      .code(ErrorCode.SUCCESS.getCode())
                      .messages(ErrorCode.SUCCESS.getMessage())
                      .build();
    }
}
