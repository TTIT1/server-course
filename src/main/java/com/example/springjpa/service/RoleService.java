package com.example.springjpa.service;

import com.example.springjpa.dto.response.RoleResponse;
import com.example.springjpa.dto.resquest.RoleRequest;

import java.util.List;

public interface RoleService {
    RoleResponse CreatRole(RoleRequest request);
    List<RoleResponse> findAll();
}
