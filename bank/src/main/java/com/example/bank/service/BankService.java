package com.example.bank.service;

import com.example.bank.entity.AccountType;
import com.example.bank.model.BankAccount;
import com.example.bank.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount createBankAccount(String accountNumber, AccountType accountType, Double balance) {
        if (bankAccountRepository.findByAccountNumber(accountNumber).isPresent()) {
            throw new RuntimeException("Account number already exists");
        }

        if (balance <= 0) {
            throw new RuntimeException("Initial balance must be a positive number");
        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(accountNumber);
        //bankAccount.setAccountType(accountType);
        bankAccount.setBalance(balance);

        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount getBankAccountByAccountNumber(String accountNumber) {
        Optional<BankAccount> optionalBankAccount = Optional.ofNullable(bankAccountRepository.findByAccountNumber(accountNumber));

        if (!optionalBankAccount.isPresent()) {
            throw new RuntimeException("Account not found");
        }

        return optionalBankAccount.get();
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount updateBankAccount(String accountNumber, AccountType accountType, Double balance) {
        BankAccount bankAccount = getBankAccountByAccountNumber(accountNumber);

        if (balance <= 0) {
            throw new RuntimeException("Balance must be a positive number");
        }

        //bankAccount.setAccountType(accountType);
        bankAccount.setBalance(balance);

        return bankAccountRepository.save(bankAccount);
    }
}
