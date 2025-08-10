package com.example.springjpa.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LaptopAttributeDTO {
    private Integer idLaptop;
    private String nameLaptop;
    private String Ram;
    private String Chip;
    private String VGA;
    private  String SSD;
    private String description;

}
