package com.ironhack.midtermproject.controller.dto;

import com.ironhack.midtermproject.classes.Money;

public class TransferSendMoneyDTO {

    private Long accountReceiverId;

    private Money amountMoney;

    private String nameReceiver;

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
