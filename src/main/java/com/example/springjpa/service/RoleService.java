package com.example.springjpa.service;

import com.example.springjpa.dto.response.RoleResponse;
import com.example.springjpa.dto.resquest.RoleRequest;

public interface RoleService {
    RoleResponse CreatRole(RoleRequest request);
}
