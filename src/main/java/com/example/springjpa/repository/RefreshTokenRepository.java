package com.example.springjpa.repository;

import com.example.springjpa.model.auth.RefreshToken;
import com.example.springjpa.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {

    Optional<RefreshToken> findByRefreshToken(String token);
    List<RefreshToken> findById (User user);
    void deleteByUser(User user);
}
