package com.example.springjpa.repository;

import com.example.springjpa.dto.response.UserResponseGet;
import com.example.springjpa.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findBygmail(String gmail);
    Optional<User> findByUserName(String userName);
    Optional<User> findById (String id);
}
