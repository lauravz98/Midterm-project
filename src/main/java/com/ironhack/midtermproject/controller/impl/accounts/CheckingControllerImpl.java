package com.ironhack.midtermproject.controller.impl.accounts;

import com.ironhack.midtermproject.controller.interfaces.accounts.CheckingController;
import com.ironhack.midtermproject.repository.accounts.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckingControllerImpl implements CheckingController {
    @Autowired
    private CheckingRepository checkingRepository;
}
