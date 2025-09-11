package com.example.springjpa.service.Impl;

import com.example.springjpa.dto.CourseDTO;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Author;
import com.example.springjpa.model.Course;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.CourseRepository;
import com.example.springjpa.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private   final AuthorRepository authorRepository;
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
            throw new AppExcepotion(ErrorCode.NOT_FOUND);
        }
        Course course = Course.builder()
                .title(courseDTO.getTitle())
                .description(courseDTO.getDescription())
                .build();
        if (courseDTO.getAuthorIds() != null){
            List<Author> authors = authorRepository.findAllById(courseDTO.getAuthorIds());
            course.setAuthors(authors);
        }
        return   courseRepository.save(course);
    }
    public List<Course> getCourse(){
//        return courseRepository.findAll().stream().map(this::toModelCourse).collect(Collectors.toList());
        return courseRepository.findAll();
    }
    public  Course update(Integer id , CourseDTO courseDTO){
        Course course    = courseRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.USER_NOT_FOUND));

        course.setDescription(courseDTO.getDescription());
        course.setTitle(courseDTO.getTitle());
        List<Author> author = authorRepository.findAllById(courseDTO.getAuthorIds());
        if (!author.isEmpty()){
            course.setAuthors(author);
        }
        courseRepository.save(course);

        return course;
    }
    public Course GetByID(Integer id){
        Course course = courseRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.USER_NOT_FOUND));
        return course;
    }
    public boolean DeleteById(Integer id){
        Boolean check = false;
        Course course = courseRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.USER_NOT_FOUND));
        check =true;
        courseRepository.deleteById(id);
        return check;

    }
}
