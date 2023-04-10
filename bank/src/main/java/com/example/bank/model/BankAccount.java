package com.example.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BankAccount {
    @Id
    private String accountNumber;
    private String accountType;
    private double balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isPresent() {
        return true;
    }
}

