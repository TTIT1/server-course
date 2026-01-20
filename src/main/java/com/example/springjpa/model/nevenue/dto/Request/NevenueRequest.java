package com.example.springjpa.model.nevenue.dto.Request;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level=AccessLevel.PRIVATE)
@Getter
@Setter
public class NevenueRequest {
    private String courseId;
}
