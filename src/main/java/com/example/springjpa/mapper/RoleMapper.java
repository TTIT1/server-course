package com.example.springjpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.springjpa.dto.response.RoleResponse;
import com.example.springjpa.dto.resquest.RoleRequest;
import com.example.springjpa.model.auth.Role;



@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    // thuộc tính permission sẽ ko đc astrac gán gi cả mà thủ công bằng tay
    Role toRole(RoleRequest request);

    @Mapping(source = "permissions", target = "permission")
    RoleResponse toRoleResponse(Role role);
    // source =thuộc tính trong entyy
    // target thuộc tính trong rolerespon
}
