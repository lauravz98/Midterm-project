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
public class TransferThirdPartySend extends Transfer{
    @ManyToOne
    @JoinColumn(name = "account_receiver_id")
    private Account accountReceiverThird;
    private String nameReceiver;
    private String secretKey;
    private String hashKey;


    public TransferThirdPartySend() {
    }

    public TransferThirdPartySend(Account accountReceiverThird, Money amount, String hashKey, String nameReceiver, String secretKey, LocalDateTime timeTransfer) {
        super(amount, timeTransfer);
        this.accountReceiverThird = accountReceiverThird;
        this.hashKey = hashKey;
        this.secretKey = secretKey;
        this.nameReceiver = nameReceiver;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
    public Long getAccountReceiverThird() {
        return accountReceiverThird.getAccountId();
    }
}
