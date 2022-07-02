package com.ironhack.midtermproject.models.users;

import com.ironhack.midtermproject.classes.Address;
import com.ironhack.midtermproject.enums.UserRoleEnum;
import com.ironhack.midtermproject.models.accounts.Account;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User{

    private Date dateOfBirth;
    @Embedded
    private Address address;

    private Integer mailingAddress; //optional

    @OneToMany(mappedBy = "primaryOwner")
    private Set<Account> primaryAccounts;

    @OneToMany(mappedBy = "secondaryOwner")
    private Set<Account> secondaryAccounts;

    public AccountHolder() {
    }

    public AccountHolder(String name, String password, Date dateOfBirth, Address address) {
        super(name, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mailingAddress = null;
        super.setUserRole(UserRoleEnum.ACCOUNT_HOLDER);
    }

    public AccountHolder(String name, String password, Date dateOfBirth, Address address, int mailingAddress) {
        super(name, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mailingAddress = mailingAddress;
        super.setUserRole(UserRoleEnum.ACCOUNT_HOLDER);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Integer mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
