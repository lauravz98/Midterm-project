package com.ironhack.midtermproject.controller.dto;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.models.users.AccountHolder;

public class CreateAccountDTO {

    private AccountHolder primaryOwner;
    private String secretKey;
    private Money balance;

    public CreateAccountDTO(AccountHolder primaryOwner, String secretKey, Money balance) {
        this.primaryOwner = primaryOwner;
        this.secretKey = secretKey;
        this.balance = balance;
    }

    public CreateAccountDTO() {
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
