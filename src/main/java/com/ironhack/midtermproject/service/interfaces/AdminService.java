package com.ironhack.midtermproject.service.interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.models.Account;
import com.ironhack.midtermproject.models.Checking;
import com.ironhack.midtermproject.models.Saving;

public interface AdminService {
    Account findAccountById(Long accountId);
    Account createCheckingAccount(Checking checkingAccount);
    void updateBalance(Long accountId, Money balance);
    void delete(Long accountId);
}
