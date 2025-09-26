package com.example.springjpa.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@Table(name = "laptop")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Laptop {

    @Id
    @GeneratedValue
      Integer id;
    @Column(name = "n_Name", unique = true, length = 255,nullable = false)
     String nameLaptop;


}
