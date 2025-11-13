package com.example.springjpa.repository;

import com.example.springjpa.model.course.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File,String> {
}
