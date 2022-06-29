package com.ironhack.midtermproject.models;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.StatusAccountEnum;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
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
    private final Money MIN_MINIMUM_BALANCE = new Money(new BigDecimal(100));

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimumBalance_max")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_minimumBalance_max"))
    })
    private final Money MAX_MINIMUM_BALANCE = new Money(new BigDecimal(1000));

    private BigDecimal interestRate;
    private final BigDecimal MIN_INTEREST_RATE = new BigDecimal(0);
    private final BigDecimal MAX_INTEREST_RATE = new BigDecimal(0.5);

    public Saving() {
        super();
    }

    public Saving(AccountHolder primaryOwner, TypeAccountEnum typeAccount, String secretKey) {
        super(primaryOwner, typeAccount, secretKey);
        setMinimumBalance(MIN_MINIMUM_BALANCE);
        setInterestRate(new BigDecimal(0.0025));
    }

    public Saving(AccountHolder primaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey) {
        super(primaryOwner, typeAccount, creationDate, secretKey);
        setMinimumBalance(MIN_MINIMUM_BALANCE);
        setInterestRate(new BigDecimal(0.0025));
    }

    public Saving(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, String secretKey) {
        super(primaryOwner, secondaryOwner, typeAccount, secretKey);
        setMinimumBalance(MIN_MINIMUM_BALANCE);
        setInterestRate(new BigDecimal(0.0025));
    }

    public Saving(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey) {
        super(primaryOwner, secondaryOwner, typeAccount, creationDate, secretKey);
        setMinimumBalance(MIN_MINIMUM_BALANCE);
        setInterestRate(new BigDecimal(0.0025));
    }

    public Saving(AccountHolder primaryOwner, TypeAccountEnum typeAccount, String secretKey, Money minimumBalance) {
        super(primaryOwner, typeAccount, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(new BigDecimal(0.0025));
    }

    public Saving(AccountHolder primaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey, Money minimumBalance) {
        super(primaryOwner, typeAccount, creationDate, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(new BigDecimal(0.0025));
    }

    public Saving(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, String secretKey, Money minimumBalance) {
        super(primaryOwner, secondaryOwner, typeAccount, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(new BigDecimal(0.0025));
    }

    public Saving(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey, Money minimumBalance) {
        super(primaryOwner, secondaryOwner, typeAccount, creationDate, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(new BigDecimal(0.0025));
    }

    public Saving(AccountHolder primaryOwner, TypeAccountEnum typeAccount, String secretKey, BigDecimal interestRate) {
        super(primaryOwner, typeAccount, secretKey);
        setMinimumBalance(MIN_MINIMUM_BALANCE);
        setInterestRate(interestRate);
    }

    public Saving(AccountHolder primaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey, BigDecimal interestRate) {
        super(primaryOwner, typeAccount, creationDate, secretKey);
        setMinimumBalance(MIN_MINIMUM_BALANCE);
        setInterestRate(interestRate);
    }

    public Saving(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, String secretKey, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, typeAccount, secretKey);
        setMinimumBalance(MIN_MINIMUM_BALANCE);
        setInterestRate(interestRate);
    }

    public Saving(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, typeAccount, creationDate, secretKey);
        setMinimumBalance(MIN_MINIMUM_BALANCE);
        setInterestRate(interestRate);
    }

    public Saving(AccountHolder primaryOwner, TypeAccountEnum typeAccount, String secretKey, Money minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, typeAccount, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Saving(AccountHolder primaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey, Money minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, typeAccount, creationDate, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Saving(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, String secretKey, Money minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, typeAccount, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Saving(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey, Money minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, typeAccount, creationDate, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        if(minimumBalance.compareTo(MIN_MINIMUM_BALANCE) <= 0){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                    , "The minimum balance is lower than the established lower limit" + MIN_MINIMUM_BALANCE);
        } else if(minimumBalance.compareTo(MAX_MINIMUM_BALANCE) <= 0 ){
            this.minimumBalance = minimumBalance;
        } else{
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                    , "Minimum balance exceeds maximum limit" + MAX_MINIMUM_BALANCE);
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate.compareTo(MIN_INTEREST_RATE) <= 0){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                    , "Interest rate invalid. Interest rate is lower than the established lower limit" + MIN_INTEREST_RATE);
        } else if(interestRate.compareTo(MAX_INTEREST_RATE) <= 0 ){
            this.interestRate = interestRate;
        } else{
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                    , "Interest rate invalid. Interest rate exceeds maximum limit" + MAX_INTEREST_RATE);
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
