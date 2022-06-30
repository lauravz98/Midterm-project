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

    @Embedded
    private Money amount;

    private String nameReceiver;

    public Transfer() {
    }

    public Transfer(Account accountSender, Account accountReceiver, Money amount, String nameReceiver) {
        this.accountSender = accountSender;
        this.accountReceiver = accountReceiver;
        this.amount = amount;
        this.nameReceiver = nameReceiver;
        this.nameSender = accountSender.getPrimaryOwner().getName();
    }
}
