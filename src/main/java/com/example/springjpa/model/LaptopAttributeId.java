package com.example.springjpa.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class LaptopAttributeId {
    private  Integer laptopid;
    private Integer attributeid;
}
