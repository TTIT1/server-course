package com.example.springjpa.repository;

import java.lang.classfile.ClassFile;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springjpa.model.wallet.Wallet;
@Repository
public interface  WalletRepository extends JpaRepository<Wallet, String> {
   
}
