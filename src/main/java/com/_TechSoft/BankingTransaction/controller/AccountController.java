package com._TechSoft.BankingTransaction.controller;

import com._TechSoft.BankingTransaction.entity.Account;
import com._TechSoft.BankingTransaction.service.AccountService;
import com._TechSoft.BankingTransaction.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {

    private AccountService accountService;
    private CustomerService customerService;

    public AccountController(AccountService accountService,CustomerService customerService) {
        this.accountService = accountService;
        this.customerService=customerService;
    }

//    @PostMapping("/create")
//    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
//        try {
//            Account savedAccount = accountService.createAccount(account);
//
//            return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/{customerId}")
    public ResponseEntity<Account> createAccountForExistingCustomer(@PathVariable int customerId) {
        try {
            Account account = new Account();
            Account savedAccount = accountService.createAccountForExistingCustomer(customerId,account);
            return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // If customer is not found, return 404
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
