package com.ironhack.midtermproject.models.transfers;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.models.accounts.Account;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TransferThirdPartyReceive extends Transfer{

    @ManyToOne
    @JoinColumn(name = "account_sender_id")
    private Account accountSenderThird;
    private String hashKey;
    private String nameSender;
    public TransferThirdPartyReceive() {
    }

    public TransferThirdPartyReceive(Account accountSenderThird, String nameSender, Money amount, String hashKey) {
        super(amount);
        this.accountSenderThird = accountSenderThird;
        this.nameSender = nameSender;
        this.hashKey = hashKey;
    }

    public Long getAccountSenderThird() {
        return accountSenderThird.getAccountId();
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }
}
