package com._TechSoft.BankingTransaction.service;

import com._TechSoft.BankingTransaction.entity.Account;
import com._TechSoft.BankingTransaction.entity.Transaction;
import com._TechSoft.BankingTransaction.repository.AccountRepository;
import com._TechSoft.BankingTransaction.repository.TransactionRepo;
import org.springframework.stereotype.Service;



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
            // get balance of account

            double balance = account.getBalance()+amt;

            transaction.setAccNumId(account);
            transaction.setAmt(amt);
            transaction.setCredit(true);
            transactionRepo.save(transaction);
            account.setBalance(balance);
            accountRepository.save(account);


            return "deposited to account num "+ accNum + "with rs :" + amt;
        }
        else {
            throw new RuntimeException(" account not fount");
        }

    }

    public String withdraw(int accNum, int amt, Transaction transaction) {



        Optional<Account> accountOptional = accountRepository.findById(accNum);



        if(accountOptional.isPresent()) {
            Account account= accountOptional.get();
            if(amt < account.getBalance()){

                // get balance of account
                double balance = account.getBalance()-amt;

                transaction.setAccNumId(account);
                transaction.setAmt(amt);
                transaction.setCredit(false);
                transactionRepo.save(transaction);
                account.setBalance(balance);
                accountRepository.save(account);


                return "withdraw amount for "+ accNum + "with rs :" + amt;
            } else {
                throw new RuntimeException("insuficient amount");
            }
        } else {
            throw new RuntimeException("account not found");
        }

    }

    public String balance(int accId) {
        Optional<Account> accountOptional = accountRepository.findById(accId);


        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return "balance :" + account.getBalance();

        } else {
            return "account not found";
        }
    }

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
