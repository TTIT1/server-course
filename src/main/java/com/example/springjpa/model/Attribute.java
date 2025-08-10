package com.example.springjpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "attribute")
public class Attribute {
     @Id
     @GeneratedValue
     private  Integer id;
     @Column(nullable = false,unique = true)
     private String Ram;
    @Column(nullable = false,unique = true)
    private  String Chip;
    @Column(nullable = false,unique = true)
    private String VGA;
    @Column(nullable = false,unique = true)
    private  String SSD;

}
