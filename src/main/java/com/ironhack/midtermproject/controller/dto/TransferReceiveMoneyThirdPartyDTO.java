package com.ironhack.midtermproject.controller.dto;

import com.ironhack.midtermproject.classes.Money;

public class TransferReceiveMoneyThirdPartyDTO {
    private Long idThirdParty;
    private Money amountMoney;
    private String hashKey;

    public Long getIdThirdParty() {
        return idThirdParty;
    }

    public void setIdThirdParty(Long idThirdParty) {
        this.idThirdParty = idThirdParty;
    }

    public Money getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(Money amountMoney) {
        this.amountMoney = amountMoney;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
