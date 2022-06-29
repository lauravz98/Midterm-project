package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.controller.interfaces.SavingController;
import com.ironhack.midtermproject.repository.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingControllerImpl implements SavingController {
    @Autowired
    private SavingRepository savingRepository;
}
