package com.ironhack.midtermproject.models.transfers;

import com.ironhack.midtermproject.classes.Money;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Money amount;
    private LocalDateTime timeTransfer;
    private LocalDate dateTransfer;

    public Transfer() {
    }

    public Transfer(Money amount, LocalDateTime timeTransfer) {
        this.timeTransfer = timeTransfer;
        this.amount = amount;
        this.dateTransfer = timeTransfer.toLocalDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeTransfer() {
        return timeTransfer;
    }

    public void setTimeTransfer(LocalDateTime timeTransfer) {
        this.timeTransfer = timeTransfer;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

}
