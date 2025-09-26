package com.example.springjpa.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@Table(name = "LaptopAttribute")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LaptopAttribute {
    @EmbeddedId
    LaptopAttributeId laptopAttributeId;
    @ManyToOne
    @MapsId("laptopid")
    @JoinColumn(name = "laptop_id")
      Laptop laptop;
    @ManyToOne
    @MapsId("attributeid")
    @JoinColumn(name = "attribute_id")
      Attribute attribute;
    @Column(name = "important")
     String description;
}
