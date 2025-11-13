package com.example.springjpa.model.course;

import com.example.springjpa.model.course.Section;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "COURSE")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course  extends  BaseEntity{

    @Column(name = "t_title",unique = true,nullable = false)
    String title;
    @Column(name = "d_description",unique = true,nullable = false)
     String description;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "course_author",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnore
     List<Author> authors = new ArrayList<>();
     @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
     List<Section> sections = new ArrayList<>();


}
