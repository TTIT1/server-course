package com.example.springjpa.model.payout;

import java.math.BigDecimal;

import com.example.springjpa.enums.revenue.PayoutStatus;
import com.example.springjpa.model.course.Author;

import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Entity;

import com.example.springjpa.model.course.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(
    name = "payout",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"author_id", "month"})
    }
)

public class Payout extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
     Author author;

    @Column(nullable = false, precision = 19, scale = 2)
     BigDecimal totalRevenue;

    @Column(nullable = false, precision = 19, scale = 2)
     BigDecimal systemFee;

    @Column(nullable = false, precision = 19, scale = 2)
     BigDecimal teacherIncome;

    @Column(nullable = false)
     String month; // ví dụ: "2025-12"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
     PayoutStatus status;
    
}
