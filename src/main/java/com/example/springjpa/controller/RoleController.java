package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.response.RoleResponse;
import com.example.springjpa.dto.resquest.RoleRequest;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    // Quản lý role – chỉ ADMIN
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('user.assign_role')")
    @PostMapping("/add/new/role")
    public ApiResponse<RoleResponse> creatRole(@RequestBody RoleRequest request) {
        RoleResponse roleResponse = roleService.CreatRole(request);
        return ApiResponse.<RoleResponse>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .messages(ErrorCode.SUCCESS.getMessage())
                .rsulte(roleResponse)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('user.view')")
    @GetMapping("/get/all/role")
    public ApiResponse<List<RoleResponse>> getAllRole() {
        return ApiResponse.<List<RoleResponse>>builder()
                .rsulte(roleService.findAll())
                .code(ErrorCode.SUCCESS.getCode())
                .messages(ErrorCode.SUCCESS.getMessage())
                .build();
    }
}
