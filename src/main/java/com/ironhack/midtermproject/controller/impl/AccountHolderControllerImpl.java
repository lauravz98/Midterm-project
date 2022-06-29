package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.controller.interfaces.AccountHolderController;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountHolderControllerImpl implements AccountHolderController {
    @Autowired
    private AccountHolderRepository accountHolderRepository;
}
