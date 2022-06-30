package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.controller.dto.AccountHolderIdDTO;
import com.ironhack.midtermproject.controller.interfaces.AccountHolderController;
import com.ironhack.midtermproject.models.Account;
import com.ironhack.midtermproject.models.AccountHolder;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.AccountRepository;
import com.ironhack.midtermproject.security.CustomUserDetails;
import com.ironhack.midtermproject.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class AccountHolderControllerImpl implements AccountHolderController {
    @Autowired
    private AccountHolderService accountHolderService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/myAccounts")
    @ResponseStatus(HttpStatus.OK)
    public Set<Account> findMyAccountsById(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return accountHolderService.findMyAccountsById(userDetails.getUser().getId());
    }
}
