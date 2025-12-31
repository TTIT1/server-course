package com.example.springjpa.model.order;
import java.math.BigDecimal;

import com.example.springjpa.enums.order.OrderStatus;
import com.example.springjpa.model.auth.User;
import com.example.springjpa.model.course.BaseEntity;
import com.example.springjpa.model.course.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@FieldDefaults(level= lombok.AccessLevel.PRIVATE)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class CourseOrder extends BaseEntity {
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

}
