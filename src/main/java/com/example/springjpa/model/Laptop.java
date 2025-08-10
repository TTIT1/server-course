package com.example.springjpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "laptop")
@Data
public class Laptop {

    @Id
    @GeneratedValue
    private  Integer id;
    @Column(name = "n_Name", unique = true, length = 255,nullable = false)
    private String nameLaptop;


}
