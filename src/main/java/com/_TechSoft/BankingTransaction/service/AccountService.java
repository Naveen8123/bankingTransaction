package com._TechSoft.BankingTransaction.service;

import com._TechSoft.BankingTransaction.entity.Account;
import com._TechSoft.BankingTransaction.entity.Customer;
import com._TechSoft.BankingTransaction.repository.AccountRepository;
import com._TechSoft.BankingTransaction.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

   // constructor DI
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository=customerRepository;
    }

//    public Account createAccount(Account account){
//        Account savedAccount = accountRepository.save(account);
//        return savedAccount;
//    }
    public Account createAccountForExistingCustomer(int customerId, Account account) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            account.setCutomerId(existingCustomer);

            Account saveAccount = accountRepository.save(account);// Save the new account
            return saveAccount;
        } else {
            throw new RuntimeException("Customer with ID " + customerId + " not found.");
        }
    }
}
