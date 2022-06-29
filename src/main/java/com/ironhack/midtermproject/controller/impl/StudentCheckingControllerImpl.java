package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.controller.interfaces.CheckingController;
import com.ironhack.midtermproject.controller.interfaces.StudentCheckingController;
import com.ironhack.midtermproject.models.StudentChecking;
import com.ironhack.midtermproject.repository.CheckingRepository;
import com.ironhack.midtermproject.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCheckingControllerImpl implements StudentCheckingController {
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
}
