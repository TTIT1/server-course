package com.example.springjpa.repository;

import com.example.springjpa.model.LaptopAttribute;
import com.example.springjpa.model.LaptopAttributeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaptopAttributeRep extends JpaRepository<LaptopAttribute,Integer> {

}