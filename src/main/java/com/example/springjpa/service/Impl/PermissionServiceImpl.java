package com.example.springjpa.service.Impl;

import com.example.springjpa.dto.response.PermisstionResponse;
import com.example.springjpa.dto.resquest.PermissionRequest;
import com.example.springjpa.mapper.PermissionMapper;
import com.example.springjpa.model.auth.Permission;
import com.example.springjpa.repository.PermisstionRepository;
import com.example.springjpa.service.PermisstionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PermissionServiceImpl implements PermisstionService {
    PermissionMapper permissionMapper;
    PermisstionRepository permisstionRepository;
    @Override
    public PermisstionResponse creatPermisstionResponse(PermissionRequest request) {
        Permission permission = new Permission();
           permission = permissionMapper.toPermission(request);
           permisstionRepository.save(permission);
           return PermisstionResponse.builder()
                   .name(permission.getName())
                   .description(permission.getDescription())
                   .build();
    }
}
