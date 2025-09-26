package com.example.springjpa.repository;

import com.example.springjpa.model.Author;
import com.example.springjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findBygmail(String gmail);
}
