package com.ironhack.midtermproject.models;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.models.accounts.Account;

import javax.persistence.*;
import java.util.List;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_sender_id")
    private Account accountSender;

    private String nameSender;

    @ManyToOne
    @JoinColumn(name = "account_receiver_id")
    private Account accountReceiver;
    private String nameReceiver;
    @Embedded
    private Money amount;



    public Transfer() {
    }

    public Transfer(Account accountSender, Account accountReceiver, Money amount, String nameSender, String nameReceiver) {
        this.accountSender = accountSender;
        this.accountReceiver = accountReceiver;
        this.amount = amount;
        this.nameReceiver = nameReceiver;
        this.nameSender = nameSender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountSender() {
        return accountSender.getAccountId();
    }

    public Long getAccountReceiver() {
        return accountReceiver.getAccountId();
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
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
