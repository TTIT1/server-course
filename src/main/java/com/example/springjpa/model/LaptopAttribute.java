package com.example.springjpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "LaptopAttribute")
public class LaptopAttribute {
    @EmbeddedId
   private LaptopAttributeId laptopAttributeId;
    @ManyToOne
    @MapsId("laptopid")
    @JoinColumn(name = "laptop_id")
    private  Laptop laptop;
    @ManyToOne
    @MapsId("attributeid")
    @JoinColumn(name = "attribute_id")
    private  Attribute attribute;
    @Column(name = "important")
    private String description;
}
