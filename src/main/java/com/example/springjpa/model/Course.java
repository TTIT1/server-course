package com.example.springjpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "COURSE")
@EqualsAndHashCode(callSuper = true)
public class Course  extends  BaseEntity{

    @Column(name = "t_title",unique = true,nullable = false)
   private String title;
    @Column(name = "d_description",unique = true,nullable = false)
    private String description;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "course_author",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnore
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();


}
