package com._TechSoft.BankingTransaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accNum;

    private double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custId",nullable = false)
    private Customer cutomerId;

    @OneToMany(mappedBy = "trasId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

}
