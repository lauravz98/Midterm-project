package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.controller.interfaces.CheckingController;
import com.ironhack.midtermproject.repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckingControllerImpl implements CheckingController {
    @Autowired
    private CheckingRepository checkingRepository;
}
