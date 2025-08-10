package com.example.springjpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "SECTION")
@EqualsAndHashCode(callSuper = true)
public class Section extends BaseEntity  {

    @Column(name = "n_name",unique = true,nullable = false)
    private  String name;
    @Column(name = "o_order",unique = true,nullable = false)
    private  int order;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "section")
    private List< Lecture> lecture;
}

