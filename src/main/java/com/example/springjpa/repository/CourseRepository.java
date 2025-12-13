package com.example.springjpa.repository;


import com.example.springjpa.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface CourseRepository  extends JpaRepository<Course, String> {
    Optional<Course> findByTitle(String title);


    List<Course> findAllById(String id);


    @Query("""
              SELECT c FROM Course c
               LEFT JOIN FETCH c.sections s
               LEFT JOIN FETCH s.lecture l
               LEFT JOIN FETCH l.resource r
              LEFT JOIN FETCH TREAT(r AS Text) ft
              LEFT JOIN FETCH TREAT(r AS Video) fv
              LEFT JOIN FETCH TREAT(r AS File) ff
               WHERE c.id = :courseId
            
            """)
    Course findByCourseId(@Param("courseId") String courseId);

}
