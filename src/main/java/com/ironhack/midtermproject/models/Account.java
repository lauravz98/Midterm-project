package com.ironhack.midtermproject.models;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.StatusAccountEnum;
import com.ironhack.midtermproject.enums.TypeAccountEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;
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
    private final Money PENALTY_FEE = new Money(new BigDecimal(40));

    @Enumerated(EnumType.STRING)
    private StatusAccountEnum statusAccount;

    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private TypeAccountEnum typeAccount;

    private LocalDateTime now;

    public Account() {
    }

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
    }
    protected Date getDateNow(){
        now = LocalDateTime.now();
        Date today = Date.from(now.toInstant((ZoneOffset) ZoneId.systemDefault()));
        return today;
    }

    public long getAccountId() {
        return accountId;
    }

    public Money getBalance() {
        return balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public Money getPENALTY_FEE() {
        return PENALTY_FEE;
    }

    public StatusAccountEnum getStatusAccount() {
        return statusAccount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public TypeAccountEnum getTypeAccount() {
        return typeAccount;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public void setStatusAccount(StatusAccountEnum statusAccount) {
        this.statusAccount = statusAccount;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setTypeAccount(TypeAccountEnum typeAccount) {
        this.typeAccount = typeAccount;
    }
}
