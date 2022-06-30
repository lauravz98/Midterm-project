package com.ironhack.midtermproject.controller.interfaces;

import com.ironhack.midtermproject.controller.dto.AccountHolderIdDTO;
import com.ironhack.midtermproject.models.Account;
import com.ironhack.midtermproject.security.CustomUserDetails;

import java.util.Set;

public interface AccountHolderController {

    Set<Account> findMyAccountsById(CustomUserDetails userDetails);
}
