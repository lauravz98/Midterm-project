package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.controller.interfaces.CreditCardController;
import com.ironhack.midtermproject.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardControllerImpl implements CreditCardController {
    @Autowired
    private CreditCardRepository creditCardRepository;
}
