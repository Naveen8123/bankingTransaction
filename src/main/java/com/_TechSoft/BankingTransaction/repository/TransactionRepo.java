package com._TechSoft.BankingTransaction.repository;

import com._TechSoft.BankingTransaction.entity.Account;
import com._TechSoft.BankingTransaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
//    @Query("SELECT t FROM Transaction t WHERE t.accNum = ?1")
//    List<Transaction> findAllById(int accNum);
}
