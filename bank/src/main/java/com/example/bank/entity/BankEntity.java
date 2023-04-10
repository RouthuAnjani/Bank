package com.example.bank.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bank_accounts")
@Data
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String accountNumber;
    @Column
    private String accountType;
    @Column
    private Double balance;

}

