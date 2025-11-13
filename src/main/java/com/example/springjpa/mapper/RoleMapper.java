package com.example.springjpa.mapper;

import com.example.springjpa.dto.response.RoleResponse;
import com.example.springjpa.dto.resquest.RoleRequest;
import com.example.springjpa.model.auth.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);
}
