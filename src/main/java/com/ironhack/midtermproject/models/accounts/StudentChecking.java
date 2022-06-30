package com.ironhack.midtermproject.models.accounts;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import com.ironhack.midtermproject.models.users.AccountHolder;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account{

    public StudentChecking() {
        super();
    }

    public StudentChecking(AccountHolder primaryOwner, String secretKey, Money balance) {
        super(primaryOwner, secretKey);
        setTypeAccount(TypeAccountEnum.STUDENT_CHECKING);
        super.setBalance(balance);
    }

    public StudentChecking(AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String secretKey, Money balance) {
        super(primaryOwner, secondaryOwner, creationDate, secretKey, balance);
        setTypeAccount(TypeAccountEnum.STUDENT_CHECKING);
    }
}
