package com.example.springjpa.repository;

import com.example.springjpa.model.AuthorCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorCourseRepository extends JpaRepository<AuthorCourse ,Integer> {
}
