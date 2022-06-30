package com.ironhack.midtermproject.controller.interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.AccountBalanceDTO;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import com.ironhack.midtermproject.models.Account;
import com.ironhack.midtermproject.models.Checking;
import com.ironhack.midtermproject.models.CreditCard;
import com.ironhack.midtermproject.models.Saving;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AdminController {

    // Get
    List<Account> findAll();
    Account findAccountById(Long accountId);
    Money getBalance(Long accountId);
    List<Account> findByTypeAccount(TypeAccountEnum typeAccount);

    // Post
    Account store(Checking checkingAccount);
    Account store(Saving savingAccount);
    Account store(CreditCard creditCard);

    // Patch
    void updateBalance(Long accountId, AccountBalanceDTO accountBalanceDTO);

    // Delete
    void delete(Long accountId);
}
