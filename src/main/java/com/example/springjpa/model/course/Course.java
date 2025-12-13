package com.example.springjpa.model.course;

import com.example.springjpa.model.course.Section;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "COURSE")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course extends BaseEntity {

    @Column(name = "t_title", unique = true, nullable = false)
    String title;

    @Column(name = "d_description", unique = true, nullable = false)
    String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "course_author",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnore
    List<Author> authors = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Section> sections = new HashSet<>();

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    List<Purchase> purchases;
}
