package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.controller.interfaces.AdminController;
import com.ironhack.midtermproject.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminControllerImpl implements AdminController {
    @Autowired
    private AdminRepository adminRepository;

}
