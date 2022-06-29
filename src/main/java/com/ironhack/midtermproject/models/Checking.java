package com.ironhack.midtermproject.models;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.TypeAccountEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimumBalance")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_minimumBalance"))
    })
    private final Money MINIMUM_BALANCE = new Money(new BigDecimal(250));

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_monthlyMaintenanceFee")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_monthlyMaintenanceFee"))
    })
    private final Money MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal(12));

    public Checking() {
        super();
    }

    public Checking(AccountHolder primaryOwner, String secretKey) {
        super(primaryOwner, secretKey);
        setTypeAccount(TypeAccountEnum.CHECKING);
    }

    public Checking(AccountHolder primaryOwner, Date creationDate, String secretKey) {
        super(primaryOwner, creationDate, secretKey);
        setTypeAccount(TypeAccountEnum.CHECKING);
    }

    public Checking(AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(primaryOwner, secondaryOwner, secretKey);
        setTypeAccount(TypeAccountEnum.CHECKING);
    }

    public Checking(AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String secretKey) {
        super(primaryOwner, secondaryOwner, creationDate, secretKey);
        setTypeAccount(TypeAccountEnum.CHECKING);
    }

    public Money getMINIMUM_BALANCE() {
        return MINIMUM_BALANCE;
    }

    public Money getMONTHLY_MAINTENANCE_FEE() {
        return MONTHLY_MAINTENANCE_FEE;
    }
}
