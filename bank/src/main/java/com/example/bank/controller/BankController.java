package com.example.bank.controller;


import com.example.bank.model.BankAccount;
import com.example.bank.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/bank_accounts")
public class BankController {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @PostMapping
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        validateBankAccount(bankAccount);
        return bankAccountRepository.save(bankAccount);
    }

    @GetMapping("/{accountNumber}")
    public BankAccount getBankAccount(@PathVariable String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @PutMapping("/{accountNumber}")
    public BankAccount updateBankAccount(@PathVariable String accountNumber, @RequestBody BankAccount bankAccount) {
        validateBankAccount(bankAccount);
        BankAccount existingBankAccount = bankAccountRepository.findByAccountNumber(accountNumber);
        existingBankAccount.setAccountType(bankAccount.getAccountType());
        existingBankAccount.setBalance(bankAccount.getBalance());
        return bankAccountRepository.save(existingBankAccount);
    }

    @DeleteMapping("/{accountNumber}")
    public void deleteBankAccount(@PathVariable String accountNumber) {
        bankAccountRepository.deleteById(accountNumber);
    }

    private void validateBankAccount(BankAccount bankAccount) {
        if (bankAccountRepository.findByAccountNumber(bankAccount.getAccountNumber()) != null) {
            throw new IllegalArgumentException("Account number already exists");
        }
        if (!Arrays.asList("checking", "savings", "money market").contains(bankAccount.getAccountType().toLowerCase())) {
            throw new IllegalArgumentException("Invalid account type");
        }
        if (bankAccount.getBalance() < 0) {
            throw new IllegalArgumentException("Initial balance must be positive");
        }
    }
}

