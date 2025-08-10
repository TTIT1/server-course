package com.example.springjpa.repository;

import com.example.springjpa.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LapTopRep extends JpaRepository<Laptop,Integer> {
}

