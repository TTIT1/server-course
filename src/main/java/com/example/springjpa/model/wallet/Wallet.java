package com.example.springjpa.model.wallet;

import java.math.BigDecimal;

import com.example.springjpa.model.auth.User;
import com.example.springjpa.model.course.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@FieldDefaults(level= lombok.AccessLevel.PRIVATE)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class Wallet extends  BaseEntity {
    
    @OneToOne(mappedBy = "wallet")
    User user;
    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal balance;

    
}
