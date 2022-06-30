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
public class Saving extends Account{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimumBalance")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_minimumBalance"))
    })
    private Money minimumBalance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimumBalance_min")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_minimumBalance_min"))
    })
    private final static Money MIN_MINIMUM_BALANCE = new Money(new BigDecimal(100));

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimumBalance_max")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_minimumBalance_max"))
    })
    private final static Money MAX_MINIMUM_BALANCE = new Money(new BigDecimal(1000));

    private BigDecimal interestRate;
    private final static BigDecimal MIN_INTEREST_RATE = new BigDecimal(0);
    private final static BigDecimal MAX_INTEREST_RATE = new BigDecimal(0.5);

    public Saving() {
        super();
    }

    public Saving(AccountHolder primaryOwner, String secretKey, Money balance) {
        super(primaryOwner, secretKey);
        setTypeAccount(TypeAccountEnum.SAVINGS);
        setMinimumBalance(MIN_MINIMUM_BALANCE);
        setInterestRate(new BigDecimal(0.0025));
        setBalance(balance);
    }

    public Saving(AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String secretKey, Money balance, Money minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, creationDate, secretKey, balance);
        setMinimumBalance(minimumBalance);
        setTypeAccount(TypeAccountEnum.SAVINGS);
        setInterestRate(interestRate);
        setBalance(balance);
    }

    public Money getMinimumBalance() {
        if(minimumBalance == null){
            this.minimumBalance = MIN_MINIMUM_BALANCE;
        }
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        if(minimumBalance == null){
            this.minimumBalance = MIN_MINIMUM_BALANCE;
        } else {
            if(minimumBalance.compareTo(MIN_MINIMUM_BALANCE) < 0){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                        , "The minimum balance is lower than the established lower limit" + MIN_MINIMUM_BALANCE);
            } else if(minimumBalance.compareTo(MAX_MINIMUM_BALANCE) <= 0 ){
                this.minimumBalance = minimumBalance;
            } else{
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                        , "Minimum balance exceeds maximum limit" + MAX_MINIMUM_BALANCE);
            }
        }

    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate == null){
            this.interestRate = new BigDecimal(0.0025);
        } else {
            if(interestRate.compareTo(MIN_INTEREST_RATE) <= 0){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                        , "Interest rate invalid. Interest rate is lower than the established lower limit" + MIN_INTEREST_RATE);
            } else if(interestRate.compareTo(MAX_INTEREST_RATE) >= 0 ){
                this.interestRate = interestRate;
            } else{
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                        , "Interest rate invalid. Interest rate exceeds maximum limit" + MAX_INTEREST_RATE);
            }
        }

    }

    @Override
    public void setBalance(Money balance) {
        if(balance.compareTo(getMinimumBalance()) < 0){
            super.setBalance(getBalance().decreaseAmount(getPENALTY_FEE()));
            //throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
            //        "Balance is lower minimum allow: " + MINIMUM_BALANCE);
        } else {
            super.setBalance(balance);
        }
    }

    public Money getMIN_MINIMUM_BALANCE() {
        return MIN_MINIMUM_BALANCE;
    }

    public Money getMAX_MINIMUM_BALANCE() {
        return MAX_MINIMUM_BALANCE;
    }

    public BigDecimal getMIN_INTEREST_RATE() {
        return MIN_INTEREST_RATE;
    }

    public BigDecimal getMAX_INTEREST_RATE() {
        return MAX_INTEREST_RATE;
    }
}
