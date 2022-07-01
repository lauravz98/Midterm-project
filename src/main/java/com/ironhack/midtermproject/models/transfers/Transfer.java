package com.ironhack.midtermproject.models.transfers;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.models.accounts.Account;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_receiver_id")
    private Account accountReceiver;
    private String nameReceiver;
    @Embedded
    private Money amount;

    public Transfer() {
    }


    public Transfer(Account accountReceiver, String nameReceiver, Money amount) {
        this.accountReceiver = accountReceiver;
        this.amount = amount;
        this.nameReceiver = nameReceiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getAccountReceiver() {
        return accountReceiver.getAccountId();
    }


    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }
}
