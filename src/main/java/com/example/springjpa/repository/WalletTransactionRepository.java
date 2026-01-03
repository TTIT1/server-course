package com.example.springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springjpa.model.wallet.WalletTransaction;

import io.lettuce.core.dynamic.annotation.Param;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, String> {
   @Query(
    value = "SELECT EXISTS ( " +
            "SELECT 1 FROM wallet_transaction WHERE wallet_id = :walletId" +
            ")",
    nativeQuery = true 
    )
     boolean existsByWalletId(@Param("wallet_id") String walletId);

}