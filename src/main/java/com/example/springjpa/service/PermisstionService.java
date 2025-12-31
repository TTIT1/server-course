package com.example.springjpa.service;

import com.example.springjpa.dto.response.PermisstionResponse;
import com.example.springjpa.dto.resquest.PermissionRequest;

import java.util.List;


public interface PermisstionService {
    PermisstionResponse creatPermisstionResponse(PermissionRequest request);
    PermisstionResponse findPermisstionResponseByName(String name);
    List<PermisstionResponse> findAllPermisstionResponseByName();
}
