package com.example.springjpa.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@Table(name = "attribute")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attribute {
     @Id
     @GeneratedValue
       Integer id;
     @Column(nullable = false,unique = true)
      String Ram;
    @Column(nullable = false,unique = true)
      String Chip;
    @Column(nullable = false,unique = true)
     String VGA;
    @Column(nullable = false,unique = true)
      String SSD;

}
