package com.ironhack.midtermproject.service.interfaces;

import com.ironhack.midtermproject.models.Account;

import java.util.Set;

public interface AccountHolderService {
    Set<Account> findMyAccountsById(Long id);
}
