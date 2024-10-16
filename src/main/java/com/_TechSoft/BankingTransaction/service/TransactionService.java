package com._TechSoft.BankingTransaction.service;

import com._TechSoft.BankingTransaction.entity.Account;
import com._TechSoft.BankingTransaction.entity.Transaction;
import com._TechSoft.BankingTransaction.repository.AccountRepository;
import com._TechSoft.BankingTransaction.repository.TransactionRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private TransactionRepo transactionRepo;

    private AccountRepository accountRepository;

    public TransactionService(TransactionRepo transactionRepo, AccountRepository accountRepository) {
        this.transactionRepo = transactionRepo;
        this.accountRepository=accountRepository;
    }

    public String deposite(int accNum, int amt, Transaction transaction) {

        Optional<Account> accountOptional = accountRepository.findById(accNum);

        if(accountOptional.isPresent()){
            Account account= accountOptional.get();
            transaction.setAccNumId(account);
            transaction.setAmt(amt);
            transaction.setCredit(true);
            transactionRepo.save(transaction);
//            echo "# bankingTransaction" >> README.md
//            git init
//            git add README.md
//            git commit -m "first commit"
//            git branch -M main
//            git remote add origin https://github.com/Naveen8123/bankingTransaction.git
//            git push -u origin main

            return "deposited to account num "+ accNum + "with rs :" + amt;
        }
        else {
            throw new RuntimeException(" account not fount");
        }

    }

//    public String withdraw(int accNum, int amt, Transaction transaction) {
//        return null;
//    }
//
//    public String balance(int accNum) {
//        Optional<Account> accountOptonal = accountRepository.findById(accNum);
//        if(accountOptonal.isPresent()){
////            Account account= accountOptonal.get();
//            List<Transaction> transactions = transactionRepo.findAllById(accNum);
//            int totalBalance = transactions.stream().mapToInt(Transaction::getAmt).sum();
//            return "balance is :" + totalBalance;
//        }
//        else {
//            return " account num is not found"+ accNum;
//        }
//
//    }
}
