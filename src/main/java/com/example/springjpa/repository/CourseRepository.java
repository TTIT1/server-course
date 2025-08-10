package com.example.springjpa.repository;

import com.example.springjpa.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CourseRepository  extends JpaRepository<Course, Integer> {
    Optional<Course> findByTitle(String title);

}
