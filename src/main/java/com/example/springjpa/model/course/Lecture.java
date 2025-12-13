package com.example.springjpa.model.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "LECTURE")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lecture extends BaseEntity {

    @Column(name = "n_name", unique = true, nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnore
    Section section;

    @OneToOne(mappedBy = "lecture")
    @JsonIgnore
    Resource resource;
}
