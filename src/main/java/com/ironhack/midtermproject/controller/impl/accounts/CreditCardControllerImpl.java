package com.ironhack.midtermproject.controller.impl.accounts;

import com.ironhack.midtermproject.controller.interfaces.accounts.CreditCardController;
import com.ironhack.midtermproject.repository.accounts.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardControllerImpl implements CreditCardController {
    @Autowired
    private CreditCardRepository creditCardRepository;
}
