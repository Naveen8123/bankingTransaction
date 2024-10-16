package com._TechSoft.BankingTransaction.service;

import com._TechSoft.BankingTransaction.entity.Account;
import com._TechSoft.BankingTransaction.entity.Customer;
import com._TechSoft.BankingTransaction.repository.AccountRepository;
import com._TechSoft.BankingTransaction.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Customer> allCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null); // Assuming JPA repository
    }

    public Customer updateCustomer(int id, Customer cust) {
        Customer customer = customerRepository.findById(id).orElse(null);

        customer.setName(cust.getName());
        customer.setEmail(cust.getEmail());
        customer.setMobile(cust.getMobile());
        customer.setAddress(cust.getAddress());
        customer.setPincode(cust.getPincode());
        Customer updatedCutomer = customerRepository.save(customer);
        return updatedCutomer;
    }

    public void deleteCustomer(int id) {
        try {
            if (customerRepository.findById(id).isPresent()) {
                customerRepository.deleteById(id);
                System.out.println("Customer with ID: " + id + " deleted successfully.");
            } else {
                throw new RuntimeException("Customer with ID: " + id + " does not exist.");
            }
        } catch (RuntimeException e) {
            // Handle the exception, log the error or rethrow
            System.err.println(e.getMessage());
            throw e; // You can also throw a custom exception if needed
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("An unexpected error occurred: " + e.getMessage());
            throw new RuntimeException("An unexpected error occurred while deleting the customer.");
        }


    }
}
