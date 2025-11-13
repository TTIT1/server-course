package com.example.springjpa.repository;

import com.example.springjpa.model.auth.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PermisstionRepository extends JpaRepository<Permission,String> {

    Optional<Permission> findAllByName(String name);
}
