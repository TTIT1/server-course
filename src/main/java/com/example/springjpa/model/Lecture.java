package com.example.springjpa.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Table(name = "LECTURE")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lecture extends BaseEntity {
    
    @Column(name = "n_name", unique = true, nullable = false)
     String name;
    @ManyToOne
    @JoinColumn(name = "section_id")
     Section section;
    @OneToOne(mappedBy = "lecture")
     Resource resource;
}
