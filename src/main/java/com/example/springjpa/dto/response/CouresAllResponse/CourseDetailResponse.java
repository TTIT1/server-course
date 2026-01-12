package com.example.springjpa.dto.response.CouresAllResponse;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailResponse {
    private List<String> authors;
    private String id;
    private String title;
    private String description;
    private BigDecimal price;

    private List<SectionResponse> sections;
}
