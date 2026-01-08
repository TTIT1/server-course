package com.example.springjpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springjpa.model.course.Author;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface AuthorRepository extends JpaRepository<Author,String> {
    Optional<Author> findByEmail(String email);
    List<Author> findAllByfirstName(String nameFind);
    List<Author> findAllByfirstNameIgnoreCase(String nameFind);
    List<Author> findAllByCourses_Id(String courseId);
    @Query("select a.id from Author a join a.courses c where c.id =: courseID")
    List<String>findAuthorIdsByCourseId(@Param("courseId") String courseId);


}
/*
find…By<Property><Keyword>
read…By<Property><Keyword>
get…By<Property><Keyword>

Property = tên field trong Entity (ví dụ firstName, lastName, age…)
Keyword = từ khóa đặc biệt mà Spring Data hiểu (ví dụ IgnoreCase, Containing, Between, In…)


 */