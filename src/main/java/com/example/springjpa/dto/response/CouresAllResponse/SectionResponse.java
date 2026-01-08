package com.example.springjpa.dto.response.CouresAllResponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionResponse {

    private String id;
    private String name;
    private Integer order;

    private List<lectureResponse> lectures;
}
