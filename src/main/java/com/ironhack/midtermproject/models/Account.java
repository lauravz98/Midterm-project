package com.ironhack.midtermproject.models;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.StatusAccountEnum;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import static com.ironhack.midtermproject.utils.utils.getDateNow;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String secretKey;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_balance"))
    })
    private Money balance;

    @ManyToOne
    @JoinColumn(name = "id_primary_owner")
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(name = "id_secondary_owner")
    private AccountHolder secondaryOwner;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_penalty_fee")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_penalty_fee"))
    })
    private static final Money PENALTY_FEE = new Money(new BigDecimal(40));

    @Enumerated(EnumType.STRING)
    private StatusAccountEnum statusAccount;

    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private TypeAccountEnum typeAccount;

    public Account() {
    }
/*
    public Account(AccountHolder primaryOwner, TypeAccountEnum typeAccount, String secretKey) {
        this.primaryOwner = primaryOwner;
        this.typeAccount = typeAccount;
        this.secondaryOwner = null;
        this.statusAccount = StatusAccountEnum.ACTIVE;
        this.creationDate = getDateNow();
        this.secretKey = secretKey;
    }

    public Account(AccountHolder primaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey) {
        this.primaryOwner = primaryOwner;
        this.typeAccount = typeAccount;
        this.secondaryOwner = null;
        this.statusAccount = StatusAccountEnum.ACTIVE;
        this.creationDate = creationDate;
        this.secretKey = secretKey;
    }

    public Account(AccountHolder primaryOwner, AccountHolder secondaryOwner,TypeAccountEnum typeAccount, String secretKey) {
        this.primaryOwner = primaryOwner;
        this.typeAccount = typeAccount;
        this.secondaryOwner = secondaryOwner;
        this.statusAccount = StatusAccountEnum.ACTIVE;
        this.creationDate = getDateNow();
        this.secretKey = secretKey;
    }

    public Account(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey) {
        this.primaryOwner = primaryOwner;
        this.typeAccount = typeAccount;
        this.secondaryOwner = secondaryOwner;
        this.statusAccount = StatusAccountEnum.ACTIVE;
        this.creationDate = creationDate;
        this.secretKey = secretKey;
    }*/

    protected Account(AccountHolder primaryOwner, String secretKey) {
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = null;
        this.statusAccount = StatusAccountEnum.ACTIVE;
        this.creationDate = getDateNow();
        this.secretKey = secretKey;
    }

    protected Account(AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String secretKey, Money balance) {
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.statusAccount = StatusAccountEnum.ACTIVE;
        setCreationDate(creationDate);
        this.secretKey = secretKey;
        setBalance(balance);
    }

    public Long getAccountId() { return accountId; }
    public Money getBalance() { return balance; }
    public AccountHolder getPrimaryOwner() { return primaryOwner; }
    public AccountHolder getSecondaryOwner() { return secondaryOwner; }
    public Money getPENALTY_FEE() { return PENALTY_FEE; }
    public StatusAccountEnum getStatusAccount() { return statusAccount; }
    public Date getCreationDate() { return creationDate; }
    public TypeAccountEnum getTypeAccount() { return typeAccount; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }

    public void setBalance(Money balance) {
        Money balanceNegative = new Money(new BigDecimal(0));
        if(balance == null){
            this.balance = balanceNegative;
        }else{
            if(balanceNegative.compareTo(balance) > 0){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "Balance is lower minimum allow: 0 USD");
            }
            this.balance = balance;
        }
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) { this.primaryOwner = primaryOwner; }
    public void setSecondaryOwner(AccountHolder secondaryOwner) { this.secondaryOwner = secondaryOwner;}
    public void setStatusAccount(StatusAccountEnum statusAccount) { this.statusAccount = statusAccount; }

    public void setCreationDate(Date creationDate) {
        if(creationDate == null){
            this.creationDate = getDateNow();
        } else {
            this.creationDate = creationDate;
        }
    }

    public String getSecretKey() { return secretKey; }
    public void setTypeAccount(TypeAccountEnum typeAccount) { this.typeAccount = typeAccount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(primaryOwner, account.primaryOwner) && typeAccount == account.typeAccount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryOwner, typeAccount);
    }
}
