package com.example.springjpa.dto.response.CouresAllResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoResponse {
    
    private String id;
    private Integer length;
    private String url;
}
