package com.example.springjpa.model.nevenue.model;

import com.example.springjpa.model.course.Author;
import com.example.springjpa.model.course.Course;
import com.example.springjpa.model.order.CourseOrder;
import com.example.springjpa.model.course.BaseEntity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;


import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@FieldDefaults(level= lombok.AccessLevel.PRIVATE)
@Getter

@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class Nevenue extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "course_order_id", nullable = false)
    private CourseOrder courseOrder;
}
