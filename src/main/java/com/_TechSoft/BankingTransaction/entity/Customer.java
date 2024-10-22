package com._TechSoft.BankingTransaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int custId;
    private String name;
    private String email;
    private String mobile;
    private String address;
    private long pincode;
    private LocalDateTime date;


    @OneToMany(mappedBy = "cutomerId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Account> accounts;
}
