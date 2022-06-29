package com.ironhack.midtermproject.models;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.TypeAccountEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
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

    public Checking(AccountHolder primaryOwner, TypeAccountEnum typeAccount, String secretKey) {
        super(primaryOwner, typeAccount, secretKey);
    }

    public Checking(AccountHolder primaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey) {
        super(primaryOwner, typeAccount, creationDate, secretKey);
    }

    public Checking(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, String secretKey) {
        super(primaryOwner, secondaryOwner, typeAccount, secretKey);
    }

    public Checking(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey) {
        super(primaryOwner, secondaryOwner, typeAccount, creationDate, secretKey);
    }

    public Money getMINIMUM_BALANCE() {
        return MINIMUM_BALANCE;
    }

    public Money getMONTHLY_MAINTENANCE_FEE() {
        return MONTHLY_MAINTENANCE_FEE;
    }
}
