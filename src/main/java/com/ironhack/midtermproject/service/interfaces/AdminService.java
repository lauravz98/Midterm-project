package com.ironhack.midtermproject.service.interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.CreditCardCreateDTO;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.models.accounts.Checking;
import com.ironhack.midtermproject.models.accounts.CreditCard;
import com.ironhack.midtermproject.models.accounts.Saving;

import java.util.List;

public interface AdminService {
    Account findAccountById(Long accountId);
    List<Account> findByTypeAccount(TypeAccountEnum typeAccount);
    Account createCheckingAccount(Checking checkingAccount);
    Saving createSavingAccount(Saving savingAccount);
    CreditCard createCreditCard(CreditCardCreateDTO creditCard);

    void updateBalance(Long accountId, Money balance);
    void delete(Long accountId);

}
