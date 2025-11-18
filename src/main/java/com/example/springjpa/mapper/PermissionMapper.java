package com.example.springjpa.mapper;

import com.example.springjpa.dto.response.PermisstionResponse;
import com.example.springjpa.dto.resquest.PermissionRequest;
import com.example.springjpa.model.auth.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);
    
    PermisstionResponse toPermissionRequest(Permission permission);

}
