package com.ironhack.midtermproject.controller.dto;

import com.ironhack.midtermproject.classes.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

public class AccountBalanceDTO {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_balance"))
    })
    private Money balance;

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
