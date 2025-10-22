//package com.example.springjpa.repository;
//
//import com.example.springjpa.model.AuthorCourse;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface AuthorCourseRepository extends JpaRepository<AuthorCourse ,Integer> {
//
//    @Query("SELECT ac.course.id FROM AuthorCourse ac WHERE ac.author.id = :authorId")
//    List<Integer> findCourseIdsByAuthorId(@Param("authorId") Integer authorId);
//}
