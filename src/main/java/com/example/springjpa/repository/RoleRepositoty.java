package com.example.springjpa.repository;

import com.example.springjpa.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepositoty extends JpaRepository<Role,String> {
 Role findByName(String name);

}
