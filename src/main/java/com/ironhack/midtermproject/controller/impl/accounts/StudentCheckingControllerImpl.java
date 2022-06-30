package com.ironhack.midtermproject.controller.impl.accounts;

import com.ironhack.midtermproject.controller.interfaces.accounts.StudentCheckingController;
import com.ironhack.midtermproject.repository.accounts.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCheckingControllerImpl implements StudentCheckingController {
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
}
