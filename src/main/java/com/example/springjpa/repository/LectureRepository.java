package com.example.springjpa.repository;

import com.example.springjpa.model.course.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,String> {


}
