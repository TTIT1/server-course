package com.example.springjpa.repository;

import com.example.springjpa.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRep extends JpaRepository<Attribute,Integer> {
}
