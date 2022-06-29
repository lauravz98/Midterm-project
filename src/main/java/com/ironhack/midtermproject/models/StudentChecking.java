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

    public StudentChecking(AccountHolder primaryOwner, String secretKey) {
        super(primaryOwner, secretKey);
        setTypeAccount(TypeAccountEnum.STUDENT_SAVINGS);
    }

    public StudentChecking(AccountHolder primaryOwner, Date creationDate, String secretKey) {
        super(primaryOwner, creationDate, secretKey);
        setTypeAccount(TypeAccountEnum.STUDENT_SAVINGS);
    }

    public StudentChecking(AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(primaryOwner, secondaryOwner, secretKey);
        setTypeAccount(TypeAccountEnum.STUDENT_SAVINGS);
    }

    public StudentChecking(AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String secretKey) {
        super(primaryOwner, secondaryOwner, creationDate, secretKey);
        setTypeAccount(TypeAccountEnum.STUDENT_SAVINGS);
    }
}
