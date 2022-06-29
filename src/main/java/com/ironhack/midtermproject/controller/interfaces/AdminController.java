package com.ironhack.midtermproject.controller.interfaces;

import com.ironhack.midtermproject.controller.dto.AccountBalanceDTO;
import com.ironhack.midtermproject.models.Account;
import com.ironhack.midtermproject.models.Checking;
import com.ironhack.midtermproject.models.CreditCard;
import com.ironhack.midtermproject.models.Saving;

import java.util.List;

public interface AdminController {

    List<Account> findAll();
    Account findAccountById(Long accountId);
    Account store(Checking checkingAccount);
    Account store(Saving savingAccount);
    Account store(CreditCard creditCard);
    void updateBalance(Long accountId, AccountBalanceDTO accountBalanceDTO);
    void delete(Long accountId);
}
