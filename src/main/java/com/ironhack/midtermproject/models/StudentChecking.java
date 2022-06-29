package com.ironhack.midtermproject.models;

import com.ironhack.midtermproject.enums.TypeAccountEnum;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account{

    public StudentChecking() {
        super();
    }

    public StudentChecking(AccountHolder primaryOwner, TypeAccountEnum typeAccount, String secretKey) {
        super(primaryOwner, typeAccount, secretKey);
    }

    public StudentChecking(AccountHolder primaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey) {
        super(primaryOwner, typeAccount, creationDate, secretKey);
    }

    public StudentChecking(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, String secretKey) {
        super(primaryOwner, secondaryOwner, typeAccount, secretKey);
    }

    public StudentChecking(AccountHolder primaryOwner, AccountHolder secondaryOwner, TypeAccountEnum typeAccount, Date creationDate, String secretKey) {
        super(primaryOwner, secondaryOwner, typeAccount, creationDate, secretKey);
    }
}
