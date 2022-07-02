package com.ironhack.midtermproject.controller.dto;

import com.ironhack.midtermproject.classes.Money;

public class TransferSendMoneyToAHFromThirdPartyDTO {
    private Money amountMoney;
    private Long idAccount;
    private String nameReceiver;
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

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

}
