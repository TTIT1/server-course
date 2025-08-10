package com.example.springjpa.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileDTO {
    private Integer id;
    private  String type;
    private String name;
    private int size;
    private String url;
    private  int  lectureid;
}
