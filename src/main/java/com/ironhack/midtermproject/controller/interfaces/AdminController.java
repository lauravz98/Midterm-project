package com.ironhack.midtermproject.controller.interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.AccountBalanceDTO;
import com.ironhack.midtermproject.controller.dto.CreditCardCreateDTO;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.models.accounts.Checking;
import com.ironhack.midtermproject.models.accounts.Saving;
import com.ironhack.midtermproject.models.users.ThirdParty;

import java.util.List;

public interface AdminController {

    // Get
    List<Account> findAllAccounts();
    Account findAccountById(Long accountId);
    Money getBalanceByAccountId(Long accountId);
    List<Account> findByTypeAccount(TypeAccountEnum typeAccount);
    List<Transfer> findAllTransfer();

    // Post
    Account createNewAccount(Checking checkingAccount);
    Account createNewAccount(Saving savingAccount);
    Account createNewAccount(CreditCardCreateDTO creditCard);

    ThirdParty createThirdParty(ThirdParty thirdParty);

    // Patch
    void updateBalanceByAccountId(Long accountId, AccountBalanceDTO accountBalanceDTO);

    // Delete
    void deleteAccount(Long accountId);
}
