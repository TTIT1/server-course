package com.example.springjpa.model.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springjpa.model.course.Course;
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

    @Query("""
    SELECT DISTINCT co.course
    FROM CourseOrder co
    JOIN co.course c
    WHERE co.user.id = :userId
    AND co.status = 'PAID'
    """)
    List<Course> findPaidCoursesByUserId(@Param("userId") String userId);



    @Query("""
    SELECT DISTINCT co
    FROM CourseOrder co
    LEFT JOIN FETCH co.course c
    LEFT JOIN FETCH c.sections s
    LEFT JOIN FETCH s.lecture l
    LEFT JOIN FETCH l.resource r
    WHERE co.user.id = :userId
    AND co.status = 'PAID'
    """)
    List<CourseOrder> findPaidOrdersWithFullCourse(@Param("userId") String userId);


    @Query("""
    SELECT COUNT(co) > 0
    FROM CourseOrder co
    WHERE co.user.id = :userId
    AND co.course.id = :courseId
    AND co.status = 'PAID'
    """)
    boolean userHasBoughtCourse(String userId, String courseId);


}
