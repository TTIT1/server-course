package com.example.springjpa.repository;

import com.example.springjpa.model.course.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video,String>{

}
