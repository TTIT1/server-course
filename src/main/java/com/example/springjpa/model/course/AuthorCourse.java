//package com.example.springjpa.model;
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.FieldDefaults;
//
//@Entity
//@Data
//@NoArgsConstructor
//@Table(name = "AuthorCourse")
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class AuthorCourse {
//
//    @EmbeddedId
//    AuthorCourseId authorCourseId;
//    @ManyToOne
//    @MapsId("authorid")
//    @JoinColumn(name = "author_id")
//    Author author;
//    @ManyToOne
//    @MapsId("courseid")
//    @JoinColumn(name = "course_id")
//    Course course;
//    @Column(name = "important")
//    String description;
//}
