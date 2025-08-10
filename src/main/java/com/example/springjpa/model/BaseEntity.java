package com.example.springjpa.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
public class BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @CreatedDate
    private LocalDateTime creatAt;
    @UpdateTimestamp
    private LocalDateTime lassModifiedAt;
    private String createBy;
    private String lastMotifiedBy;
}
