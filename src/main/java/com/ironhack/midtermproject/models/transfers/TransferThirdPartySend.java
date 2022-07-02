package com.ironhack.midtermproject.models.transfers;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.models.accounts.Account;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TransferThirdPartySend extends Transfer{
    @ManyToOne
    @JoinColumn(name = "account_receiver_id")
    private Account accountReceiverThird;

    private String hashKey;
    private String secretKey;

    public TransferThirdPartySend() {
    }

    public TransferThirdPartySend(Account accountReceiverThird, Money amount, String hashKey, String secretKey) {
        super(amount);
        this.accountReceiverThird = accountReceiverThird;
        this.hashKey = hashKey;
        this.secretKey = secretKey;
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
