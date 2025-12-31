package com.example.springjpa.model.course;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "SECTION")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Section extends BaseEntity {

    @Column(name = "n_name", unique = true, nullable = false)
    String name;

    @Column(name = "o_order", unique = true, nullable = false)
    int order;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    Course course;

    @OneToMany(mappedBy = "section")
    @JsonIgnore
    Set<Lecture> lecture;
}
