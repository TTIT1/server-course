package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.response.PermisstionResponse;
import com.example.springjpa.dto.resquest.PermissionRequest;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.service.PermisstionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermisstionService permisstionService;

    // Quản lý permission – chỉ ADMIN
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('settings.update')")
    @PostMapping("/add/permission")
    public ApiResponse<PermisstionResponse> creatPermission(@RequestBody PermissionRequest request) {
        PermisstionResponse permisstionResponse = permisstionService.creatPermisstionResponse(request);
        return ApiResponse.<PermisstionResponse>builder()
                .rsulte(permisstionResponse)
                .code(ErrorCode.SUCCESS.getCode())
                .messages(ErrorCode.SUCCESS.getMessage())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('user.view')")
    @GetMapping("/get/by/name")
    public ApiResponse<PermisstionResponse> getPermisstionByName(@RequestParam String name) {
        PermisstionResponse permisstionResponse = permisstionService.findPermisstionResponseByName(name);

        return ApiResponse.<PermisstionResponse>builder()
                .rsulte(permisstionResponse)
                .code(ErrorCode.SUCCESS.getCode())
                .messages(ErrorCode.SUCCESS.getMessage())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('user.view')")
    @GetMapping("/get/all/permission")
    public ApiResponse<List<PermisstionResponse>> getAllPermisstion() {
        List<PermisstionResponse> permisstionResponses = permisstionService.findAllPermisstionResponseByName();
        return ApiResponse.<List<PermisstionResponse>>builder()
                .rsulte(permisstionResponses)
                .code(ErrorCode.SUCCESS.getCode())
                .messages(ErrorCode.SUCCESS.getMessage())
                .build();
    }

}
