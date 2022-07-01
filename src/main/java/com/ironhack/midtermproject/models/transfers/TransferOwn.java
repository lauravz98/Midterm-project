package com.ironhack.midtermproject.models.transfers;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.models.accounts.Account;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TransferOwn extends Transfer{
    @ManyToOne
    @JoinColumn(name = "account_sender_id")
    private Account accountSender;

    private String nameSender;

    public TransferOwn() {
    }

    public TransferOwn(Account accountReceiver, String nameReceiver, Money amount, Account accountSender, String nameSender) {
        super(accountReceiver, nameReceiver, amount);
        this.accountSender = accountSender;
        this.nameSender = nameSender;
    }


    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }
    public Long getAccountSender() {
        return accountSender.getAccountId();
    }


}
