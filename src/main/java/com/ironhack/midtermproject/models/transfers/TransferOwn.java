package com.ironhack.midtermproject.models.transfers;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.models.accounts.Account;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TransferOwn extends Transfer{
    @ManyToOne
    @JoinColumn(name = "account_receiver_id")
    private Account accountReceiverOwn;
    private String nameReceiver;
    @ManyToOne
    @JoinColumn(name = "account_sender_id")
    private Account accountSenderOwn;

    private String nameSender;

    public TransferOwn() {
    }

    public TransferOwn(Account accountReceiverOwn, String nameReceiver, Money amount, Account accountSenderOwn, String nameSender, LocalDateTime timeTransfer) {
        super(amount, timeTransfer);
        this.accountReceiverOwn = accountReceiverOwn;
        this.nameReceiver = nameReceiver;
        this.accountSenderOwn = accountSenderOwn;
        this.nameSender = nameSender;
    }

    public Long getAccountReceiverOwn() {
        return accountReceiverOwn.getAccountId();
    }
    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }
    public Long getAccountSenderOwn() {
        return accountSenderOwn.getAccountId();
    }
    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }


}
