package com.example.springjpa.dto.response.CouresAllResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextResponse {
    
    private String id;
    private String name;
    private String url;
}
