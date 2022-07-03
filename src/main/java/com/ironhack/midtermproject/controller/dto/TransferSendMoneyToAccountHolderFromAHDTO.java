package com.ironhack.midtermproject.controller.dto;

import com.ironhack.midtermproject.classes.Money;

public class TransferSendMoneyToAccountHolderFromAHDTO {

    private Long accountReceiverId;
    private String nameReceiver;
    private Money amountMoney;

    public TransferSendMoneyToAccountHolderFromAHDTO() {
    }

    public TransferSendMoneyToAccountHolderFromAHDTO(Long accountReceiverId, String nameReceiver, Money amountMoney) {
        this.accountReceiverId = accountReceiverId;
        this.nameReceiver = nameReceiver;
        this.amountMoney = amountMoney;
    }

    public Long getAccountReceiverId() {
        return accountReceiverId;
    }

    public void setAccountReceiverId(Long accountReceiverId) {
        this.accountReceiverId = accountReceiverId;
    }

    public Money getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(Money amountMoney) {
        this.amountMoney = amountMoney;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }
}
