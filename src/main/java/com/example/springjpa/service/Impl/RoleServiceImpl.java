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
import org.springframework.stereotype.Service;

import java.util.Set;

@Service

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleMapper roleMapper;
    RoleRepositoty roleRepositoty;
PermisstionRepository permisstionRepository;

    @Override
    public RoleResponse CreatRole(RoleRequest request) {
        String kt = request.getPermission().toString();
      Permission permission = permisstionRepository.findAllByName(kt)
              .orElseThrow(() -> new AppExcepotion(ErrorCode.USER_NOT_FOUND));


        Role role   =  roleMapper.toRole(request);
         role.setPermissions(Set.of(permission));
        roleRepositoty.save(role);
        return RoleResponse.builder()
                .name(role.getName())
                .description(role.getDescription())

                .build();
    }
}
