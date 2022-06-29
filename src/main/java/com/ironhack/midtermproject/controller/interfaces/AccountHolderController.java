package com.ironhack.midtermproject.controller.interfaces;

import com.ironhack.midtermproject.models.Account;

import java.util.Set;

public interface AccountHolderController {

    Set<Account> findAllAccounts();
}
