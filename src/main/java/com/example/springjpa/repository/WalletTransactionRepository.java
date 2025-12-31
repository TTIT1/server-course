package com.example.springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springjpa.model.wallet.WalletTransaction;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, String> {
    
}
