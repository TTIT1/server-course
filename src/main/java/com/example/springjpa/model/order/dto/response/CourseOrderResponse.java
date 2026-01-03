package com.example.springjpa.model.order.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.springjpa.enums.order.OrderStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@FieldDefaults(level= lombok.AccessLevel.PRIVATE)
@Getter
@Setter
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CourseOrderResponse {
     String userId;
     String courseId;
     BigDecimal price;
     OrderStatus status;
}
