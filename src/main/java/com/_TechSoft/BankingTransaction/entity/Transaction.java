package com._TechSoft.BankingTransaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trasId;
    private double amt;
    private boolean credit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accNum",nullable = false)
    private Account accNumId;
}
