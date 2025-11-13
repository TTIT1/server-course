package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.response.RoleResponse;
import com.example.springjpa.dto.resquest.RoleRequest;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.repository.RoleRepositoty;
import com.example.springjpa.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping("/add/new/role")
    public ApiResponse<RoleResponse> creatRole (@RequestBody RoleRequest request){
        RoleResponse  roleResponse= roleService.CreatRole(request);
        return ApiResponse.<RoleResponse>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .messages(ErrorCode.SUCCESS.getMessage())
                .rsulte(roleResponse)
                .build();

    }
}
