package com.example.springjpa.dto.resquest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LaptopAttributeDTO {
     Integer idLaptop;
     String nameLaptop;
     String Ram;
     String Chip;
     String VGA;
      String SSD;
     String description;

}
