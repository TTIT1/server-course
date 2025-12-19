package com.example.springjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springjpa.model.auth.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findBygmail(String gmail);
    Optional<User> findByUserName(String userName);
    Optional<User> findById (String id);
}
