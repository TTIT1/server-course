package com.example.springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springjpa.model.auth.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}

