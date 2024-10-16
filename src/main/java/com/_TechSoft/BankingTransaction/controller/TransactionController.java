package com._TechSoft.BankingTransaction.controller;

import com._TechSoft.BankingTransaction.entity.Transaction;
import com._TechSoft.BankingTransaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("{accNum}")
    public String deposite(@PathVariable int accNum, @RequestParam int amt,
                                           @RequestBody Transaction transaction){
        String deposite = transactionService.deposite(accNum, amt, transaction);

        return deposite;
    }

//    @PostMapping("{accNum}")
//    public String withdraw(@PathVariable int accNum, @RequestParam int amt,
//                           @RequestBody Transaction transaction){
//        String deposite = transactionService.withdraw(accNum, amt, transaction);
//
//        return deposite;
//    }

//    @GetMapping("/balance/{accNum}")
//    public String balance(@PathVariable int accNum){
//        String balance = transactionService.balance(accNum);
//
//        return balance;
//    }

}
