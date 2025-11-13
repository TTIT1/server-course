package com.example.springjpa.model.course;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "SECTION")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Section extends BaseEntity  {

    @Column(name = "n_name",unique = true,nullable = false)
      String name;
    @Column(name = "o_order",unique = true,nullable = false)
      int order;
    @ManyToOne
    @JoinColumn(name = "course_id")
  //  @JsonIgnore
     Course course;
    @OneToMany(mappedBy = "section")
  //  @JsonIgnore
     List< Lecture> lecture;
}

