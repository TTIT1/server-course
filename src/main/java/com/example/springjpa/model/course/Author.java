package com.example.springjpa.model.course;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name ="AUTHOR_TBL")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author extends BaseEntity {

    @Column(name = "f_name", length = 50, nullable = false)
     String firstName;
    @Column(name = "l_name", length = 50, nullable = false)
     String lastName;
    @Column(name = "g_email", unique = true, nullable = false, length = 255)
     String email;
    @Column(name = "p_password",unique = false,nullable = false)
    String password;

    @Column(name = "dob", nullable = false)
    LocalDate dob;
    @ManyToMany(mappedBy = "authors")
    //@JsonIgnore
    List<Course> courses = new ArrayList<>();
    @Column(name = "role", unique = false, nullable = false)
    Set<String> roles;
    


}
