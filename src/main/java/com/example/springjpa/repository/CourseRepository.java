package com.example.springjpa.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springjpa.model.course.Course;
@Repository

public interface CourseRepository  extends JpaRepository<Course, String> {
    Optional<Course> findByTitle(String title);
    
   @Query("select c from Course c where c.id = :courseId")
   Course findByCourseId(@Param("courseId") String courseId);


    }
