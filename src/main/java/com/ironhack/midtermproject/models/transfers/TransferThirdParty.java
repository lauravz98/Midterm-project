package com.ironhack.midtermproject.models.transfers;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.models.accounts.Account;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class TransferThirdParty extends Transfer{
    private String hashKey;

    public TransferThirdParty() {
    }

    public TransferThirdParty(Account accountReceiver, String nameReceiver, Money amount, String hashKey) {
        super(accountReceiver, nameReceiver, amount);
        this.hashKey = hashKey;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
