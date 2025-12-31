package com.example.springjpa.repository;

import com.example.springjpa.model.course.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text,String> {
}
