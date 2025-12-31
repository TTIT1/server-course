package com.example.springjpa.model.wallet;
import java.math.BigDecimal;

import com.example.springjpa.enums.wallet.WalletTransactionStatus;
import com.example.springjpa.enums.wallet.WalletTransactionType;
import com.example.springjpa.model.course.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

public class WalletTransaction extends BaseEntity {
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WalletTransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WalletTransactionStatus status;
    
}
