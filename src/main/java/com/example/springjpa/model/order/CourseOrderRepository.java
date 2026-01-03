package com.example.springjpa.model.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.lettuce.core.dynamic.annotation.Param;

public interface CourseOrderRepository extends JpaRepository<CourseOrder, String>{
    
// check user đã mua khóa học đó
    @Query("SELECT p FROM " +
       "CourseOrder p " +
       "WHERE p.user.id = :userId" +
       " AND p.course.id = :courseId")

    CourseOrder findByUserIdAndCourseId (
    @Param("userId") String userId,
    @Param("courseId") String courseId
);


    // @Query("SELECT COUNT(p) > 0 FROM CourseOrder p " +
    //        "WHERE p.user.id = :userId AND p.course.id = :courseId")
    // boolean existsByUserIdAndCourseId(@Param("userId") String userId,
    //                                   @Param("courseId") String courseId);
    boolean existsByUserIdAndCourseId(String userId, String courseId);




}
