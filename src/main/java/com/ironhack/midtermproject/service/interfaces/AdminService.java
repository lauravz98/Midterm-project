package com.ironhack.midtermproject.service.interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import com.ironhack.midtermproject.models.Account;
import com.ironhack.midtermproject.models.Checking;
import com.ironhack.midtermproject.models.CreditCard;
import com.ironhack.midtermproject.models.Saving;

import java.util.List;

public interface AdminService {
    Account findAccountById(Long accountId);
    List<Account> findByTypeAccount(TypeAccountEnum typeAccount);
    Account createCheckingAccount(Checking checkingAccount);
    Saving createSavingAccount(Saving savingAccount);
    CreditCard createCreditCard(CreditCard creditCard);

    void updateBalance(Long accountId, Money balance);
    void delete(Long accountId);

}
