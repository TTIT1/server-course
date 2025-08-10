package com.example.springjpa.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VideoDTO {
    private Integer id;
    private Integer length;
    private String name;
    private int size;
    private String url;
    private  int  lectureid;
}
