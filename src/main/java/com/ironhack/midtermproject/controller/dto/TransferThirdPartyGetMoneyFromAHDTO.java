package com.ironhack.midtermproject.controller.dto;

import com.ironhack.midtermproject.classes.Money;

public class TransferThirdPartyGetMoneyFromAHDTO {
    private Money amountMoney;
    private String namePrimaryOwner;

    private Long idAccount;
    private String secretKey;

    public Money getAmountMoney() {
        return amountMoney;
    }
    public void setAmountMoney(Money amountMoney) {
        this.amountMoney = amountMoney;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getNamePrimaryOwner() {
        return namePrimaryOwner;
    }

    public void setNamePrimaryOwner(String namePrimaryOwner) {
        this.namePrimaryOwner = namePrimaryOwner;
    }
}
