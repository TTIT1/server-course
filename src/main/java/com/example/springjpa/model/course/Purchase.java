package com.example.springjpa.model.course;

import java.time.LocalDateTime;

import com.example.springjpa.model.auth.User;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE ,makeFinal=true)
@SuperBuilder
@EqualsAndHashCode(callSuper = true)

public class Purchase extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

     LocalDateTime purchasedAt;


}
