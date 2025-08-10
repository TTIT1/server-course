package com.example.springjpa.service;

import com.example.springjpa.config.notification;
import com.example.springjpa.dto.AuthorDTO;
import com.example.springjpa.dto.CourseDTO;
import com.example.springjpa.model.Author;
import com.example.springjpa.model.Course;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    AuthorRepository authorRepository;
    public CourseDTO toModelCourse(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setDescription(course.getDescription());
        courseDTO.setTitle(course.getTitle());
       List<Integer> listgetid = course.getAuthors().stream().map(Author::getId).collect(Collectors.toList());
       courseDTO.setAuthorIds(listgetid);
        courseDTO.setId(course.getId());
        return  courseDTO;
    }
    public Course save(CourseDTO courseDTO) {
       if (courseRepository.findByTitle(courseDTO.getTitle()).isPresent()) {
           throw new EntityNotFoundException("Course already exists");
       }
       Course savedCourse = new Course();
       savedCourse.setTitle(courseDTO.getTitle());
       savedCourse.setDescription(courseDTO.getDescription());
       if (courseDTO.getAuthorIds() != null){
           List<Author> authors = authorRepository.findAllById(courseDTO.getAuthorIds());
           savedCourse.setAuthors(authors);
       }
       return   courseRepository.save(savedCourse);
    }
    public List<CourseDTO> getCourse(){
        return courseRepository.findAll().stream().map(this::toModelCourse).collect(Collectors.toList());

    }
    public  CourseDTO update(Integer id , CourseDTO courseDTO){
           Course course    = courseRepository.findById(id).orElseThrow(()->new notification("Server don't find data to id = "+id));
     course.setDescription(courseDTO.getDescription());
     course.setTitle(courseDTO.getTitle());
       List<Author> author = authorRepository.findAllById(courseDTO.getAuthorIds());
       if (!author.isEmpty()){
           course.setAuthors(author);
       }

       return toModelCourse(courseRepository.save(course));
    }

}
