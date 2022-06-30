package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.models.Account;
import com.ironhack.midtermproject.models.AccountHolder;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.AccountRepository;
import com.ironhack.midtermproject.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    public Set<Account> findMyAccountsById(Long id) {
        AccountHolder accountHolder = accountHolderRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account Holder not found. Invalid account holder ID"));

        List<Account> accountsList = accountRepository.findByPrimaryOwner(accountHolder);
        Set<Account> accounts = new HashSet<>(accountsList);
        return accounts;
    }
}
