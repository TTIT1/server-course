package com.example.springjpa.ai.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.FilterDefs;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Builder
public class ChatResponse {
    String messger;
}
