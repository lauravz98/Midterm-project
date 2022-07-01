package com.ironhack.midtermproject.controller.dto;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.models.accounts.Account;

import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class TransferGetDateDTO {

    private Long id;

    private String nameSender;

    private Money amount;

    private String nameReceiver;
}
