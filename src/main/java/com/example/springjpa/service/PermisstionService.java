package com.example.springjpa.service;

import com.example.springjpa.dto.response.PermisstionResponse;
import com.example.springjpa.dto.resquest.PermissionRequest;



public interface PermisstionService {
    PermisstionResponse creatPermisstionResponse(PermissionRequest request);
}
