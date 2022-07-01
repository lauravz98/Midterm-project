package com.ironhack.midtermproject.models.accounts;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import com.ironhack.midtermproject.models.users.AccountHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.ironhack.midtermproject.utils.utils.convertToLocalDateViaInstant;

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
    private static final Money MIN_CREDIT_LIMIT  = new Money(new BigDecimal(100));

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_creditLimit_max")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_creditLimit_max"))
    })
    private static final Money MAX_CREDIT_LIMIT = new Money(new BigDecimal(100000));

    private BigDecimal interestRate;
    private static final BigDecimal MIN_INTEREST_RATE = new BigDecimal(0.1);
    private static final BigDecimal MAX_INTEREST_RATE = new BigDecimal(0.2);

    public CreditCard() {
        super();
    }

    public CreditCard(AccountHolder primaryOwner, String secretKey, Money balance) {
        super(primaryOwner, secretKey);
        setCreditLimit(MIN_CREDIT_LIMIT);
        setInterestRate(MAX_INTEREST_RATE);
        setTypeAccount(TypeAccountEnum.CREDIT_CARD);
        setBalance(balance);
    }

    public CreditCard(AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate,
                      String secretKey, Money balance, Money creditLimit, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, creationDate, secretKey, balance);
        setTypeAccount(TypeAccountEnum.CREDIT_CARD);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public Money getCreditLimit() {
        if(creditLimit == null){
            this.creditLimit = MIN_CREDIT_LIMIT;
        }
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        if(creditLimit == null){
            this.creditLimit = MIN_CREDIT_LIMIT;
        }else {
            if(creditLimit.getAmount().compareTo(MIN_CREDIT_LIMIT.getAmount()) != 1){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                        , "The credit limit is lower than the established lower limit" + MIN_CREDIT_LIMIT);
            } else if(creditLimit.getAmount().compareTo(MAX_CREDIT_LIMIT.getAmount()) != 1){
                this.creditLimit = creditLimit;
            } else{
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                        , "The credit limit exceeds maximum limit" + MAX_CREDIT_LIMIT);
            }
        }
    }

    public BigDecimal getInterestRate() {
        if(interestRate == null){
            this.interestRate = MAX_INTEREST_RATE;
        }
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate == null){
            this.interestRate = MAX_INTEREST_RATE;
        } else{
            if(getMIN_INTEREST_RATE().compareTo(interestRate) == 1){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                        , "Interest rate invalid. Interest rate is lower than the established lower limit" + MIN_INTEREST_RATE);
            } else if(interestRate.compareTo(MAX_INTEREST_RATE) != 1 ){
                this.interestRate = interestRate;
            } else{
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY
                        , "Interest rate invalid. Interest rate exceeds maximum limit" + MAX_INTEREST_RATE);
            }
        }

    }

    @Override
    public Money getBalance() {
        long months;
        if(getLastConsult() == null){
            setLastConsult(LocalDate.now());
            LocalDate localDateCreation = convertToLocalDateViaInstant(getCreationDate());
            months = ChronoUnit.MONTHS.between(localDateCreation, getLastConsult());

        } else {
            months = ChronoUnit.MONTHS.between(getLastConsult(), LocalDate.now());
        }

        int monthsInt = Integer.valueOf((int) Math.floor(months));
        BigDecimal interestRateMonthly = getInterestRate().divide(new BigDecimal(12.0), 4, RoundingMode.HALF_UP);
        BigDecimal compoundInterest = interestRateMonthly.add(new BigDecimal(1.0)).pow(monthsInt);
        BigDecimal newBalance = super.getBalance().getAmount().multiply(compoundInterest);

        Money newBalanceMoney = new Money(newBalance);
        setLastConsult(LocalDate.now());
        setBalance(newBalanceMoney);
        return newBalanceMoney;
    }
    public Money getMIN_CREDIT_LIMIT() {
        return MIN_CREDIT_LIMIT;
    }

    public Money getMAX_CREDIT_LIMIT() {
        return MAX_CREDIT_LIMIT;
    }

    public BigDecimal getMIN_INTEREST_RATE() {
        return MIN_INTEREST_RATE.setScale(4, RoundingMode.CEILING);
    }

    public BigDecimal getMAX_INTEREST_RATE() {
        return MAX_INTEREST_RATE.setScale(4, RoundingMode.CEILING);
    }
}
