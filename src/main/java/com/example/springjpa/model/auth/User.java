package com.example.springjpa.model.auth;
import com.example.springjpa.model.course.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Table(name = "User_Auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity {
    @Column(name = "g_email", unique = true, nullable = false, length = 255)
     String gmail;

    @Column(name = "name", unique = true, nullable = false)
     String userName;

    @Column(name = "password", unique = false, nullable = false)
     String passwordUser;
    @Column(name = "dob",unique = false,nullable = false)
    LocalDate dob;
    @Column(name = "role", unique = false, nullable = false)
    @ManyToMany
     Set<Role> roles;


}
