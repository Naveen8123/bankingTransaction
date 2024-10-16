package com._TechSoft.BankingTransaction.service;

import com._TechSoft.BankingTransaction.entity.Account;
import com._TechSoft.BankingTransaction.entity.Customer;
import com._TechSoft.BankingTransaction.repository.AccountRepository;
import com._TechSoft.BankingTransaction.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    public CustomerService(CustomerRepository customerRepository,AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository=accountRepository;
    }

    public Customer createCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        // Save associated accounts if they exist
        Set<Account> accounts = customer.getAccounts();
        if (accounts != null) {
            for (Account account : accounts) {
                account.setCutomerId(account.getCutomerId());  // Set the saved customer reference here
                accountRepository.save(account);
            }
        }
        return savedCustomer;
    }

    public Optional<Customer> findCustomer(int custId){
        Optional<Customer> customer = customerRepository.findById(custId);
        return customer;

    }
}
