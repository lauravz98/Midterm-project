package com.ironhack.midtermproject.controller.impl.accounts;

import com.ironhack.midtermproject.controller.interfaces.accounts.SavingController;
import com.ironhack.midtermproject.repository.accounts.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingControllerImpl implements SavingController {
    @Autowired
    private SavingRepository savingRepository;
}
