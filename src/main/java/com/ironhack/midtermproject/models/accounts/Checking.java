package com.ironhack.midtermproject.models.accounts;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import com.ironhack.midtermproject.models.users.AccountHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    private final static Money MINIMUM_BALANCE = new Money(new BigDecimal(250));

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_monthlyMaintenanceFee")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_monthlyMaintenanceFee"))
    })
    private final static Money MONTHLY_MAINTENANCE_FEE = new Money(new BigDecimal(12));;

    public Checking() {
        super();
    }

    public Checking(AccountHolder primaryOwner, String secretKey, Money balance) {
        super(primaryOwner, secretKey);
        setTypeAccount(TypeAccountEnum.CHECKING);
        setBalance(balance);
    }

    public Checking(AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String secretKey, Money balance) {
        super(primaryOwner, secondaryOwner, creationDate, secretKey, balance);
        setTypeAccount(TypeAccountEnum.CHECKING);
    }

    public Money getMINIMUM_BALANCE() {
        return MINIMUM_BALANCE;
    }

    public Money getMONTHLY_MAINTENANCE_FEE() {
        return MONTHLY_MAINTENANCE_FEE;
    }

    @Override
    public void setBalance(Money balance) {
        if(balance.getAmount().compareTo(getMINIMUM_BALANCE().getAmount()) != 1){
            super.setBalance(getBalance().decreaseAmount(getPENALTY_FEE()));
            System.out.println("A penalty of 40 USD will be applied because the balance is less than the minimum allowed. ");
            //throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
            //        "Balance is lower minimum allow: " + MINIMUM_BALANCE);
        } else {
            super.setBalance(balance);
        }
    }
}
