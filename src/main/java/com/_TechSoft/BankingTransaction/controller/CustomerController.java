package com._TechSoft.BankingTransaction.controller;

import com._TechSoft.BankingTransaction.entity.Customer;
import com._TechSoft.BankingTransaction.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    // Constructor based DI
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            Customer savedCustomer = customerService.createCustomer(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);  // Return the saved customer with HTTP 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  // In case of error, return 500 Internal Server Error
        }
    }
    @GetMapping("/allCustomers")
    public ResponseEntity<List<Customer>> allCustomers() {
        List<Customer> customers = customerService.allCustomer();
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        Customer customer = customerService.getCustomerById(id); // Assuming you have this method in your service layer
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the customer is not found
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id,
                                                   @RequestBody Customer cust) {
        Customer customer = customerService.updateCustomer(id,cust); // Assuming you have this method in your service layer
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the customer is not found
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id) {
        try {
            // Call the service layer method to delete the customer
            customerService.deleteCustomer(id);  // Ensure this method throws an exception if customer not found

            // Return a success message if deletion is successful
            return new ResponseEntity<>("Customer deleted successfully.", HttpStatus.OK);
        } catch (RuntimeException e) {
            // Return a 404 Not Found status if the customer does not exist
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle any unexpected errors
            return new ResponseEntity<>("An error occurred while deleting the customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
