package com.ironhack.midtermproject.models;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_creditLimit")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_creditLimit"))
    })
    private Money creditLimit;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_creditLimit_min")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_creditLimit_min"))
    })
    private final Money MIN_CREDIT_LIMIT  = new Money(new BigDecimal(100));

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_creditLimit_max")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_creditLimit_max"))
    })
    private final Money MAX_CREDIT_LIMIT = new Money(new BigDecimal(100000));

    private BigDecimal interestRate;
    private final BigDecimal MIN_INTEREST_RATE = new BigDecimal(0.1);
    private final BigDecimal MAX_INTEREST_RATE = new BigDecimal(0.2);

    public CreditCard() {
        super();
    }

    public CreditCard(AccountHolder primaryOwner, String secretKey, Money balance) {
        super(primaryOwner, secretKey);
        setCreditLimit(MIN_CREDIT_LIMIT);
        setInterestRate(MAX_INTEREST_RATE);
        setTypeAccount(TypeAccountEnum.CREDIT);
        setBalance(balance);
    }

    public CreditCard(AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate,
                      String secretKey, Money creditLimit, BigDecimal interestRate, Money balance) {
        super(primaryOwner, secondaryOwner, creationDate, secretKey, balance);
        setTypeAccount(TypeAccountEnum.CREDIT);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        if(creditLimit == null){
            this.creditLimit = MIN_CREDIT_LIMIT;
        }else {
            if(creditLimit.compareTo(MIN_CREDIT_LIMIT) <= 0){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                        , "The credit limit is lower than the established lower limit" + MIN_CREDIT_LIMIT);
            } else if(creditLimit.compareTo(MAX_CREDIT_LIMIT) <= 0 ){
                this.creditLimit = creditLimit;
            } else{
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                        , "The credit limit exceeds maximum limit" + MAX_CREDIT_LIMIT);
            }
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate == null){
            this.interestRate = MAX_INTEREST_RATE;
        } else{
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

    }

    public Money getMIN_CREDIT_LIMIT() {
        return MIN_CREDIT_LIMIT;
    }

    public Money getMAX_CREDIT_LIMIT() {
        return MAX_CREDIT_LIMIT;
    }

    public BigDecimal getMIN_INTEREST_RATE() {
        return MIN_INTEREST_RATE;
    }

    public BigDecimal getMAX_INTEREST_RATE() {
        return MAX_INTEREST_RATE;
    }
}
