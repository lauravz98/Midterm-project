package com.ironhack.midtermproject.controller.dto;

import com.ironhack.midtermproject.classes.Money;

public class TransferSendMoneyToThirdPartyFromAHDTO {
    private Long idThirdParty;
    private Money amountMoney;
    private String hashKey;

    public TransferSendMoneyToThirdPartyFromAHDTO(Long idThirdParty, Money amountMoney, String hashKey) {
        this.idThirdParty = idThirdParty;
        this.amountMoney = amountMoney;
        this.hashKey = hashKey;
    }

    public TransferSendMoneyToThirdPartyFromAHDTO() {
    }

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
