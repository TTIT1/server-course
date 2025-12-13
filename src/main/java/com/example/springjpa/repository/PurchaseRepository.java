package com.example.springjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.springjpa.model.course.Purchase;

@Repository
public interface  PurchaseRepository extends JpaRepository<Purchase, String> {
    Optional<Purchase> findById(String id);

    @Query("SELECT p FROM " +
            "Purchase p " +
            "WHERE p.user.id = :userId" +
            " AND p.course.id = :courseId")

    Purchase findByUserIdAndCourseId (
        @Param("userId") String userId,
         @Param("courseId") String courseId
        );

    @Query("""
            SELECT COUNT(p) > 0 
            FROM Purchase p
            WHERE p.user.id = :userId AND p.course.id = :courseId
            
            """)
    Boolean hasPurchase (@Param("userId") String userId,
                         @Param("courseId") String courseId
                         );



}
