package com.example.springjpa.service.Impl;

import com.example.springjpa.dto.response.RoleResponse;
import com.example.springjpa.dto.resquest.RoleRequest;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.mapper.PermissionMapper;
import com.example.springjpa.mapper.RoleMapper;
import com.example.springjpa.model.auth.Permission;
import com.example.springjpa.model.auth.Role;
import com.example.springjpa.repository.PermisstionRepository;
import com.example.springjpa.repository.RoleRepositoty;
import com.example.springjpa.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleMapper roleMapper;
    RoleRepositoty roleRepositoty;
PermisstionRepository permisstionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public RoleResponse CreatRole(RoleRequest request) {
         Role role = roleMapper.toRole(request);

         var permission = permisstionRepository.findAllById(request.getPermission());
         log.warn(permission);
         role.setPermissions(new HashSet<>(permission));
         roleRepositoty.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> findAll() {
      return roleRepositoty.findAll()
              .stream()
              .map(roleMapper::toRoleResponse)
              .toList();
    }
}
